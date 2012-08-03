package freenet.winterface.web.core;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import freenet.winterface.core.Configuration;
import freenet.winterface.core.FreenetWrapper;
import freenet.winterface.core.ServerManager;
import freenet.winterface.web.Dashboard;
import freenet.winterface.web.ErrorPage;

/**
 * {@link WebApplication} of Winterface.
 * 
 * @author pausb
 * 
 */
public class WinterfaceApplication extends WebApplication {

	/** FreenetWrapper to interact with node */
	private FreenetWrapper freenetWrapper;
	/** User configuration */
	private Configuration config;

	@Override
	protected void init() {
		super.init();
		// Gather all browser data
		getRequestCycleSettings().setGatherExtendedBrowserInfo(true);
		// Configuring custom mapper
		WinterMapper mapper = new WinterMapper(getRootRequestMapper());
		setRootRequestMapper(mapper);
		freenetWrapper = (FreenetWrapper) getServletContext().getAttribute(ServerManager.FREENET_ID);
		config = (Configuration) getServletContext().getAttribute(ServerManager.CONFIG_ID);
		// Add Auto-Linking
		getMarkupSettings().setAutomaticLinking(true);
		mountPage("/error", ErrorPage.class);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Dashboard.class;
	}

	/**
	 * @return {@link FreenetWrapper} to interact with core
	 */
	public FreenetWrapper getFreenetWrapper() {
		return freenetWrapper;
	}

	/**
	 * @return user {@link Configuration}
	 */
	public Configuration getConfiguration() {
		return config;
	}

}
