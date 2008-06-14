/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.component.property;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.PrintJob;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.peer.ButtonPeer;
import java.awt.peer.CanvasPeer;
import java.awt.peer.CheckboxMenuItemPeer;
import java.awt.peer.CheckboxPeer;
import java.awt.peer.ChoicePeer;
import java.awt.peer.DesktopPeer;
import java.awt.peer.DialogPeer;
import java.awt.peer.FileDialogPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.FramePeer;
import java.awt.peer.LabelPeer;
import java.awt.peer.ListPeer;
import java.awt.peer.MenuBarPeer;
import java.awt.peer.MenuItemPeer;
import java.awt.peer.MenuPeer;
import java.awt.peer.PanelPeer;
import java.awt.peer.PopupMenuPeer;
import java.awt.peer.ScrollPanePeer;
import java.awt.peer.ScrollbarPeer;
import java.awt.peer.TextAreaPeer;
import java.awt.peer.TextFieldPeer;
import java.awt.peer.WindowPeer;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToolkitFactory {
    private static Logger LOG = Logger.getLogger(ToolkitFactory.class.getName());
    
    // BEGIN: lookup.init.toolkit
    public Toolkit getDefaultToolkit () {
        java.awt.Toolkit t = null;
        String classname = System.getProperty ("java.awt.Toolkit");
        if (classname != null) {
            try {
                Class c = Class.forName (classname);
                t = (java.awt.Toolkit)c.newInstance ();
            } catch (Exception ex) {
                LOG.log(Level.WARNING, "Cannot initialize toolkit: " + classname, ex);
            }
        }
        // fallback 
        if (t == null) {
            t = new GenericAWTToolkit ();
        }
        return t;
    }
    // END: lookup.init.toolkit
    
    private static final class GenericAWTToolkit extends Toolkit {
        @Override
        protected DesktopPeer createDesktopPeer(Desktop target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected ButtonPeer createButton(Button target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected TextFieldPeer createTextField(TextField target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected LabelPeer createLabel(Label target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected ListPeer createList(List target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected CheckboxPeer createCheckbox(Checkbox target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected ScrollbarPeer createScrollbar(Scrollbar target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected ScrollPanePeer createScrollPane(ScrollPane target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected TextAreaPeer createTextArea(TextArea target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected ChoicePeer createChoice(Choice target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected FramePeer createFrame(Frame target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected CanvasPeer createCanvas(Canvas target) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected PanelPeer createPanel(Panel target) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected WindowPeer createWindow(Window target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected DialogPeer createDialog(Dialog target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected MenuBarPeer createMenuBar(MenuBar target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected MenuPeer createMenu(Menu target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected PopupMenuPeer createPopupMenu(PopupMenu target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected MenuItemPeer createMenuItem(MenuItem target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected FileDialogPeer createFileDialog(FileDialog target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected CheckboxMenuItemPeer createCheckboxMenuItem(CheckboxMenuItem target) throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected FontPeer getFontPeer(String name, int style) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Dimension getScreenSize() throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getScreenResolution() throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        public ColorModel getColorModel() throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        public String[] getFontList() {
            throw new UnsupportedOperationException();
        }

        @Override
        public FontMetrics getFontMetrics(Font font) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void sync() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Image getImage(String filename) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Image getImage(URL url) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Image createImage(String filename) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Image createImage(URL url) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean prepareImage(Image image, int width, int height, ImageObserver observer) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int checkImage(Image image, int width, int height, ImageObserver observer) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Image createImage(ImageProducer producer) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Image createImage(byte[] imagedata, int imageoffset, int imagelength) {
            throw new UnsupportedOperationException();
        }

        @Override
        public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void beep() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Clipboard getSystemClipboard() throws HeadlessException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected EventQueue getSystemEventQueueImpl() {
            throw new UnsupportedOperationException();
        }

        @Override
        public DragSourceContextPeer createDragSourceContextPeer(DragGestureEvent dge) throws InvalidDnDOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isModalityTypeSupported(ModalityType modalityType) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isModalExclusionTypeSupported(ModalExclusionType modalExclusionType) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Map<TextAttribute, ?> mapInputMethodHighlight(InputMethodHighlight highlight) throws HeadlessException {
            throw new UnsupportedOperationException();
        }
    }
}
