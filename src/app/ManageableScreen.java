package app;


/**
 *
 * @author vkachenyuk
 */
public interface ManageableScreen {
    
    public void setScreenManager(ScreenManager screenManager);
    public void showNextScreen(ManageableScreen manageableScreen);
    public void close();
    
}
