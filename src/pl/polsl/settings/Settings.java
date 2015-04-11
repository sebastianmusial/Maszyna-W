package pl.polsl.settings;

import java.util.Map;
import java.util.HashMap;

public class Settings {
	private boolean manualControlEnabled = false;
	private TrackLevel trackLevel = TrackLevel.LOW;
	private Architecture architectureType = Architecture.W;
	private Map<Extension, Boolean> extensions = new HashMap<>();
	
	public Settings() {
		for(Extension extension : Extension.values())
			extensions.put(extension, false);
	}
	
	public boolean isManualControlEnabled() {
		return manualControlEnabled;
	}
	
	public void setManualControlEnabled(boolean enabled) {
		manualControlEnabled = enabled;
	}
	
	public TrackLevel getTrackLevel() {
		return trackLevel;
	}
	
	public void setTrackLevel(TrackLevel trackLevel) {
		this.trackLevel = trackLevel;
	}
	
	public Architecture getArchitecture() {
		return architectureType;
	}
	
	public void setArchitecture(Architecture type) {
		architectureType = type;
	}
	
	public Map<Extension, Boolean> getExtensions() {
		return extensions;
	}
	
	public void setExtensionEnabled(Extension extension, Boolean enabled) {
		extensions.replace(extension, enabled);
	}
}
