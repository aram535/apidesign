/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.livedb.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import org.apidesign.livedb.LiveDB;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
@SupportedAnnotationTypes("org.apidesign.livedb.LiveDB")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@ServiceProvider(service=Processor.class)
public class LiveDBProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(LiveDB.class)) {
            LiveDB db = e.getAnnotation(LiveDB.class);
            PackageElement pe = (PackageElement)e;
            String clsName = pe.getQualifiedName().toString() + "." + db.classname();
            try {
                JavaFileObject src = processingEnv.getFiler().createSourceFile(clsName, pe);
                Writer w = src.openWriter();
                w.append("package " + pe.getQualifiedName() + ";\n");
                w.append("class " + db.classname() + " {\n");
                w.append("  public String " + db.field() + ";\n");
                w.append("}");
                w.close();
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return true;
    }

}
