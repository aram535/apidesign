package org.apidesign.misuse.projectconfig;

public class ProjectConfigurationOriginal {
    // BEGIN: misuse.prjconfig.orig
    interface ProjectConfigurationProvider {
        ProjectConfiguration[] getConfigurations();
        ProjectConfiguration getActive();
        void setActive(ProjectConfiguration c);
    }
    interface ProjectConfiguration {
        public String getDisplayName();
    }
    // END: misuse.prjconfig.orig
}
