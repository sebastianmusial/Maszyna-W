package pl.polsl.settings;

import java.util.Map;
import java.util.HashMap;

import pl.polsl.runner.CommandList;
import pl.polsl.runner.DefaultCommandList;

/**
 * Settings storage.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Settings {
	/** If true user can manually modify signal states. */
	private boolean manualControlEnabled = false;
	/** Selected track level. */
	private TrackLevel trackLevel = TrackLevel.LOW;
	/** Selected architecture. */
	private Architecture architectureType = Architecture.W;
	/** Collection mapping Extension to is state (true == enabled, false == disabled). */
	private Map<Extension, Boolean> extensions = new HashMap<>();
	
	/**
	 * Default constructor. Initializes extension states to false.
	 */
	public Settings() {
		for(Extension extension : Extension.values())
			extensions.put(extension, false);
	}
	
	/**
	 * Check if manual control is enabled.
	 * @return Value indicating if manual control is enabled.
	 */
	public boolean isManualControlEnabled() {
		return manualControlEnabled;
	}
	
	/**
	 * Change manual control status.
	 * @param enabled if true manual control is enabled, otherwise it is disabled.
	 */
	public void setManualControlEnabled(boolean enabled) {
		manualControlEnabled = enabled;
	}
	
	/**
	 * Getter of current track level.
	 * @return Current track level.
	 */
	public TrackLevel getTrackLevel() {
		return trackLevel;
	}
	
	/**
	 * Setter of current track level.
	 * @param trackLevel track level to be set
	 */
	public void setTrackLevel(TrackLevel trackLevel) {
		this.trackLevel = trackLevel;
	}
	
	/**
	 * Getter of current architecture.
	 * @return Current architecture.
	 */
	public Architecture getArchitecture() {
		return architectureType;
	}
	
	/**
	 * Setter of current architecture.
	 * @param architecture architecture to be set
	 */
	public void setArchitecture(Architecture architecture) {
		architectureType = architecture;
	}
	
	/**
	 * Getter of all extensions states.
	 * @return Collection mapping Extension to is state (true == enabled, false == disabled).
	 */
	public Map<Extension, Boolean> getExtensions() {
		return extensions;
	}
	
	/**
	 * Set single Extension state.
	 * @param extension which extension will be changed
	 * @param enabled extension enabled (true) or disabled (false)
	 */
	public void setExtensionEnabled(Extension extension, Boolean enabled) {
		extensions.replace(extension, enabled);
	}
	
	public CommandList getCommandList() {
		return new DefaultCommandList();
	}
}
