/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.livedb.impl;

import java.io.IOException;
import java.io.Writer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                Connection c = getConnection(db.url(), db.user(), db.password());
                CallableStatement s = c.prepareCall(db.query());
                ResultSet rs = s.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                w.append("package " + pe.getQualifiedName() + ";\n");
                w.append("import java.util.List;\n");
                w.append("import java.util.ArrayList;\n");
                w.append("import java.sql.*;\n");
                w.append("class " + db.classname() + " {\n");
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    w.append("  public final " + md.getColumnClassName(i) + " " + md.getColumnName(i) + ";\n");
                }
                w.append("  private " + db.classname() + "(\n");
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    w.append("    " + md.getColumnClassName(i) + " " + md.getColumnName(i));
                    if (i < md.getColumnCount()) {
                        w.append(",\n");
                    } else {
                        w.append("\n");
                    }
                }
                w.append("  ) {\n");
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    w.append("    this." + md.getColumnName(i) + " = " + md.getColumnName(i) + ";\n");
                }
                w.append("  }\n");
                w.append("  public static List<" + db.classname() + "> query() throws SQLException {\n");
                w.append("    Connection c = DriverManager.getConnection(\"" + db.url() + "\", \"" + db.user() + "\", \"" + db.password() +"\");\n");
                w.append("    List<" + db.classname() + "> res = new ArrayList<" + db.classname() + ">();\n");
                w.append("    CallableStatement s = c.prepareCall(\"" + db.query() + "\");\n");
                w.append("    ResultSet rs = s.executeQuery();\n");
                w.append("    ResultSetMetaData md = rs.getMetaData();\n");
                w.append("    while (rs.next()) {\n");
                w.append("      res.add(new " + db.classname() + "(\n");
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    w.append("        (" + md.getColumnClassName(i) + ")rs.getObject(" + i + ")");
                    if (i < md.getColumnCount()) {
                        w.append(",\n");
                    } else {
                        w.append("\n");
                    }
                }
                w.append("      ));\n");
                w.append("    };\n");
                w.append("    return res;\n");
                w.append("  }");
                w.append("}");
                w.close();
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return true;
    }

    private static Connection getConnection(String url, String user, String password) 
    throws SQLException {
        final ClassLoader cl = LiveDBProcessor.class.getClassLoader();
        for (Driver d : ServiceLoader.load(Driver.class, cl)) {
//            System.out.println("looked up: " + d);
            if (d.acceptsURL(url)) {
                //System.out.println("accepts: " + d);
                Properties p = new Properties();
                p.put("user", user);
                p.put("password", password);
                return d.connect(url, p);
            }
        }
        throw new SQLException("No driver found for " + url);
    }
}
