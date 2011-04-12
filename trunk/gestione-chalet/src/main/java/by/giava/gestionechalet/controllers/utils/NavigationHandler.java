package by.giava.gestionechalet.controllers.utils;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class NavigationHandler extends
		javax.faces.application.NavigationHandler {

	public final static String REDIRECT_PREFIX = "redirect";

	public void handleNavigation(FacesContext facesContext, String fromAction,
			String outcome) {
		if (outcome == null || outcome.compareTo("") == 0)
			return; // no navigation
		System.out.println("from: " + fromAction + " - outcome:" + outcome);
		ViewHandler viewHandler = facesContext.getApplication()
				.getViewHandler();
		String targetViewId = getTargetViewId(facesContext, outcome);

		// if (isRedirect(outcome)) {
		ExternalContext externalContext = facesContext.getExternalContext();
		String redirectPath = viewHandler.getActionURL(facesContext,
				targetViewId);
		System.out.println("redirectPath: " + redirectPath);
		try {
			externalContext.redirect(externalContext
					.encodeActionURL(redirectPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// } else {
		// UIViewRoot viewRoot = viewHandler.createView(facesContext,
		// targetViewId);
		// facesContext.setViewRoot(viewRoot);
		// facesContext.renderResponse();
		// }
	}

	private boolean isRedirect(String outcome) {
		return outcome.startsWith(REDIRECT_PREFIX);
	}

	// private String getTargetViewId(FacesContext facesContext, String outcome)
	// {
	// String targetViewId;
	// String viewSuffix = getDefaultViewSuffix(facesContext);
	//
	// if (isRedirect(outcome)) {
	// targetViewId = "/" + outcome.split(":")[1] + viewSuffix;
	// } else {
	// targetViewId = "/" + outcome + viewSuffix;
	// }
	//
	// return targetViewId;
	// }

	private String getDefaultViewSuffix(FacesContext facesContext) {
		String suffix = facesContext.getExternalContext().getInitParameter(
				"javax.faces.DEFAULT_SUFFIX");

		return suffix != null ? suffix : ".jsp";
	}

	private String getTargetViewId(FacesContext facesContext, String outcome) {
		String targetViewId;

		if (isRedirect(outcome)) {
			targetViewId = outcome.split(":")[1];
		} else {
			targetViewId = outcome;
		}
		return targetViewId;
	}

}