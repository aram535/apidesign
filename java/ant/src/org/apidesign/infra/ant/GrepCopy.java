package org.apidesign.infra.ant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterSet;
import org.apache.tools.ant.util.FileUtils;
import org.openide.util.Exceptions;

public class GrepCopy extends Task {
    private GrepFilter filter = new GrepFilter();
    private File dir;
    
    public GrepCopy() {
    }

    public FileSet createFileSet() {
        return filter.createFileSet();
    }

    public void setTarget(File dir) {
        this.dir = dir;
    }

    @Override
    public void execute() throws BuildException {
        filter.setProject(getProject());

        FilterSet set = filter.createFilterSet();

        for (Object object : set.getFilterHash().entrySet()) {

            FileWriter w = null;
            try {
                Entry en = (Entry) object;
                File to = new File(dir, (String) en.getKey());
                to.getParentFile().mkdirs();
                w = new FileWriter(to);
                w.write((String) en.getValue());
                w.close();
            } catch (IOException ex) {
                throw new BuildException(ex);
            }
        }
    }
}
