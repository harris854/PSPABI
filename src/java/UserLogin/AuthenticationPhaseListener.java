package UserLogin;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AuthenticationPhaseListener implements PhaseListener {

    private static final String USER_LOGIN_OUTCOME = "login";

    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();

        if (userExists(context)) {
            // allow processing of the requested view
            return;
        } else // send the user to the login view
        if (requestingSecureView(context)) {
            context.responseComplete();
            context.getApplication().
                    getNavigationHandler().handleNavigation(context,
                            null,
                            USER_LOGIN_OUTCOME);
        }
    }

    public void beforePhase(PhaseEvent event) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(UserManager.USER_SESSION_KEY));
    }

    private boolean requestingSecureView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        String path = extContext.getRequestPathInfo();
        return (!"/login.xhtml".equals(path) && !"/create.xhtml".equals(path));
    }
}
