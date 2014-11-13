package org.unsch.impl.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.unsch.AppController;
import org.unsch.adapter.ActionBinding;
import org.unsch.adapter.ActionComponent;
import org.unsch.adapter.MVPAdapter;
import org.unsch.adapter.MVPBinding;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;
import org.unsch.annotation.Action;
import org.unsch.annotation.Actions;
import org.unsch.annotation.MVP;
import org.unsch.annotation.Model;
import org.unsch.exception.ActionNotFoundException;
import org.unsch.impl.swing.SwingAdapter;
import org.unsch.impl.swing.utils.LoggerUtils;

public class AppControllerReflect implements AppController {

	private Logger logger = LoggerUtils.getLogger();

	public static final MVPAdapter DEFAULT_ADAPTER = new SwingAdapter();

	private MVPAdapter currentAdapter = DEFAULT_ADAPTER;
	private Map<String, ActionViewPresenterInfo> actionInfoMap = new HashMap<String, ActionViewPresenterInfo>();
	private Map<String, ModelViewInfo> modelViewInfoMap = new HashMap<String, ModelViewInfo>();
	private Map<Object, Object> mapViewModel = new HashMap<Object, Object>();
	private MVPBinding mvpBinding;

	@Override
	public MVPBinding bind(Object view, Object model, Object presenter) {

		mvpBinding = new MVPBindingImpl();
		bindModel(view, model,mvpBinding);
		bindPresenter(view, presenter,mvpBinding);
		return mvpBinding;

	}

	@Override
	public MVPBinding bindModel(Object view, Object model) {
		mvpBinding = new MVPBindingImpl();
		bindModel(view, model, mvpBinding);
		return mvpBinding;

	}

	@Override
	public MVPBinding bindPresenter(Object view, Object presenter) {
		mvpBinding = new MVPBindingImpl();
		bindPresenter(view, presenter, mvpBinding);
		return mvpBinding;

	}

	private MVPBinding bindModel(Object view, Object model,
			MVPBinding mvpBinding) {

		logger.info("Bind View :" + view.getClass().getName().toString()
				+ " with model :" + model.getClass().getName().toString());
		
		mvpBinding.setView(view);
		mvpBinding.setModel(model);

		if (modelViewInfoMap.get(view.getClass().toString()) == null) {
			processView(view.getClass());
		}
		ModelViewInfo modelViewInfo = modelViewInfoMap.get(view.getClass()
				.toString());
		if (mapViewModel.get(view) == null) {
			mapViewModel.put(view, model);
		}

		List<ModelInfo> modelsInfo = modelViewInfo.getModelsInfo();
		for (ModelInfo modelInfo : modelsInfo) {
			try {
				Method method = modelInfo.getMethod();
				Object object = method.invoke(view);
				Class<? extends ModelComponent> componentModelClass = currentAdapter
						.getComponentModel(object.getClass());
				Constructor<? extends ModelComponent> constructor = componentModelClass
						.getConstructor();
				// ModelComponent componentModel = (ModelComponent) constructor
				// .newInstance(new ModelBindingImpl(view, model,
				// modelInfo));
				ModelComponent componentModel = (ModelComponent) constructor
						.newInstance();
				componentModel
						.init(new ModelBindingImpl(view, model, modelInfo,mvpBinding));
				modelInfo.setComponentModel(componentModel);
				componentModel.bind();

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		logger.info("Exit Bind View :" + view.getClass().getName().toString()
				+ " with model :" + model.getClass().getName().toString());
		return mvpBinding;

	}

	private MVPBinding bindPresenter(Object view, Object presenter,
			MVPBinding mvpBinding) {
		logger.info("Bind View :" + view.getClass().getName().toString()
				+ " with presenter :"
				+ presenter.getClass().getName().toString());

		mvpBinding.setView(view);
		mvpBinding.setPresenter(presenter);

		if (actionInfoMap.get(view.getClass().toString()) == null) {
			processView(view.getClass());
		}
		ActionViewPresenterInfo actionViewPresenterInfo = actionInfoMap
				.get(view.getClass().toString());

		List<ActionInfo> actionsInfo = actionViewPresenterInfo.getActionsInfo();
		for (ActionInfo actionInfo : actionsInfo) {
			try {
				Object component = actionInfo.getMethod().invoke(view);

				Class componentActionClass = currentAdapter
						.getComponentAction(component.getClass());
				Constructor constructor = componentActionClass.getConstructor();

				// ActionComponent componentAction = (ActionComponent)
				// constructor
				// .newInstance(new ActionBindingImpl(view, presenter,
				// actionInfo));
				ActionComponent componentAction = (ActionComponent) constructor
						.newInstance();
				componentAction.init(new ActionBindingImpl(view, presenter,
						actionInfo));
				componentAction.bind();

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		logger.info(" Exit Bind View :" + view.getClass().getName().toString()
				+ " with presenter :"
				+ presenter.getClass().getName().toString());
		return mvpBinding;

	}

	private void processView(Class<?> viewClass) {

		logger.info("Parsing View");

		if (viewClass.getAnnotation(MVP.class) == null) {
			logger.fatal("MVP annotation is missed " + viewClass.getName());
			throw new IllegalArgumentException();
		}
		MVP mvp = viewClass.getAnnotation(MVP.class);

		List<ActionInfo> actionsInfo = new ArrayList<ActionInfo>();
		List<ModelInfo> modelsInfo = new ArrayList<ModelInfo>();

		Field[] listFieldsView = viewClass.getDeclaredFields();
		Method[] listMethodsView = viewClass.getMethods();
		Class presenterClass = mvp.presenterClass();
		Method[] listMethodsPresenter = presenterClass.getMethods();
		Class modelClass = mvp.modelClass();

		for (Field field : listFieldsView) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Action.class)) {
				Method method = findMethod(field, viewClass);
				Action action = field.getAnnotation(Action.class);
				actionsInfo.add(initActionInfo(method, action));
			}
		}

		for (Method method : listMethodsView) {
			if (method.isAnnotationPresent(Action.class)) {
				if (!fieldIsAnnotated(method, viewClass)) {
					Action action = method.getAnnotation(Action.class);
					actionsInfo.add(initActionInfo(method, action));
				}
			}
		}

		for (Field field : listFieldsView) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Model.class)) {
				Method method = findMethod(field, viewClass);
				Model model = field.getAnnotation(Model.class);
				modelsInfo.add(initModelInfo(method, model));
			}
		}

		for (Method method : listMethodsView) {
			if (method.isAnnotationPresent(Model.class)) {
				Model model = method.getAnnotation(Model.class);
				if (!fieldIsAnnotated(method, viewClass)) {
					modelsInfo.add(initModelInfo(method, model));
				}

			}
		}

		for (Field field : listFieldsView) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Actions.class)) {
				Method method = findMethod(field, viewClass);
				Actions actions = field.getAnnotation(Actions.class);
				Action[] listActions = actions.value();

				for (Action action : listActions) {
					actionsInfo.add(initActionInfo(method, action));
				}
			}
		}

		for (Method method : listMethodsView) {
			if (method.isAnnotationPresent(Actions.class)) {
				if (!fieldIsAnnotated(method, viewClass)) {
					Actions actions = method.getAnnotation(Actions.class);
					Action[] listActions = actions.value();

					for (Action action : listActions) {
						actionsInfo.add(initActionInfo(method, action));
					}
				}
			}
		}

		for (ActionInfo actionInfo : actionsInfo) {
			for (Method method : listMethodsPresenter) {
				if (method.getName().equals(actionInfo.getAction())) {
					logger.info("Action in Presenter Match ===> Action name: "
							+ actionInfo.getAction() + "  Method name: "
							+ method.getName());
					actionInfo.setActionMethod(method);
				}
			}
			if (actionInfo.getActionMethod() == null) {
				logger.fatal("Action " + actionInfo.getAction() + " not match ");
				throw new ActionNotFoundException(actionInfo.getAction(),
						presenterClass);
			}
		}

		ActionViewPresenterInfo actionViewPresenterInfo = processViewPresenter(
				viewClass, presenterClass, actionsInfo);

		actionInfoMap.put(viewClass.toString(), actionViewPresenterInfo);

		ModelViewInfo modelViewInfo = processViewModel(viewClass, modelClass,
				modelsInfo);

		modelViewInfoMap.put(viewClass.toString(), modelViewInfo);
		logger.info("Exit Parsing View");

	}

	private ActionViewPresenterInfo processViewPresenter(Class<?> viewClass,
			Class<?> presenterClass, List<ActionInfo> actionsInfo) {
		for (ActionInfo actionInfo : actionsInfo) {
			logger.info("----- Action Name: " + actionInfo.getAction()
					+ " Method: " + actionInfo.getMethod().getName()
					+ " Action Method: "
					+ actionInfo.getActionMethod().getName()
					+ " Action EventType: "
					+ actionInfo.getEventType().getName());
		}
		ActionViewPresenterInfo actionViewPresenterInfo = new ActionViewPresenterInfo();
		actionViewPresenterInfo.setViewClass(viewClass);
		actionViewPresenterInfo.setPresenterClass(presenterClass);
		actionViewPresenterInfo.setActionsInfo(actionsInfo);
		return actionViewPresenterInfo;
	}

	private ModelViewInfo processViewModel(Class<?> viewClass,
			Class<?> modelClass, List<ModelInfo> modelsInfo) {
		for (ModelInfo modelInfo : modelsInfo) {
			logger.info("----- Property Name: " + modelInfo.getPropertyName()
					+ " Init Property Name: " + modelInfo.getIniPropertyName()
					+ " Method: " + modelInfo.getMethod().getName());
		}
		ModelViewInfo modelViewInfo = new ModelViewInfo();
		modelViewInfo.setViewClass(viewClass);
		modelViewInfo.setModelClass(modelClass);
		modelViewInfo.setModelsInfo(modelsInfo);
		return modelViewInfo;
	}

	@Override
	public void refreshView(Object view) {
		if (mapViewModel.get(view) == null) {
			logger.error("no model attached to this view " + view.toString());
			throw new IllegalArgumentException();
		}

		ModelViewInfo modelViewInfo = modelViewInfoMap.get(view.getClass()
				.toString());
		List<ModelInfo> modelsInfo = modelViewInfo.getModelsInfo();

		for (ModelInfo modelInfo : modelsInfo) {
			ModelComponent componentModel = modelInfo.getComponentModel();
			componentModel.unbind();
			componentModel.bind();
		}

	}

	@Override
	public void setAdapter(MVPAdapter adapter) {
		currentAdapter = adapter;

	}

	private ActionInfo initActionInfo(Method method, Action action) {
		ActionInfo actionInfo = new ActionInfo();
		actionInfo.setAction(action.name());
		actionInfo.setMethod(method);
		actionInfo.setEventType(action.EventType());
		actionInfo.setEventAction(action.EventAction());
		return actionInfo;
	}

	private ModelInfo initModelInfo(Method method, Model model) {

		ModelInfo modelInfo = new ModelInfo();
		modelInfo.setPropertyName(model.property());
		modelInfo.setIniPropertyName(model.initProperty());
		modelInfo.setMethod(method);
		return modelInfo;
	}

	private Method findMethod(Field field, Class<?> viewClass) {
		Method method = null;
		try {

			method = viewClass.getDeclaredMethod("get"
					+ (field.getName().charAt(0) + "").toUpperCase()
					+ field.getName().substring(1));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return method;
	}

	private boolean fieldIsAnnotated(Method method, Class<?> viewClass) {
		boolean annotated = false;
		try {

			Field field = viewClass.getDeclaredField((method.getName()
					.charAt(3) + "").toLowerCase()
					+ method.getName().substring(4));
			field.setAccessible(true);
			if (field.isAnnotationPresent(Action.class)
					|| field.isAnnotationPresent(Model.class)
					|| field.isAnnotationPresent(Actions.class)) {
				annotated = true;
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return annotated;
	}

	public Map<String, ActionViewPresenterInfo> getActionInfoMap() {
		return actionInfoMap;
	}

	public void setActionInfoMap(
			Map<String, ActionViewPresenterInfo> actionInfoMap) {
		this.actionInfoMap = actionInfoMap;
	}

	public MVPAdapter getCurrentAdapter() {
		return currentAdapter;
	}

	public void setCurrentAdapter(MVPAdapter currentAdapter) {
		this.currentAdapter = currentAdapter;
	}

	public Map<String, ModelViewInfo> getModelViewInfoMap() {
		return modelViewInfoMap;
	}

	public void setModelViewInfoMap(Map<String, ModelViewInfo> modelViewInfoMap) {
		this.modelViewInfoMap = modelViewInfoMap;
	}

	public MVPBinding getMvpBinding() {
		return mvpBinding;
	}

}
