package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

/**
 * Default {@link Link} which is styled by bootstrap.
 * <p/>
 * <p>
 * You can use a link like:
 * <p/>
 * <pre>
 * add(new TypedLink(&quot;myLink&quot;)
 * {
 *     public void onClick()
 *     {
 *         // do something here...
 *     }
 * );
 * </pre>
 * <p/>
 * and in your HTML file:
 * <p/>
 * <pre>
 *  &lt;a href=&quot;#&quot; wicket:id=&quot;myLink&quot;&gt;click here&lt;/a&gt;
 * </pre>
 * <p/>
 * </p>
 * The following snippet shows how to pass a parameter from the Page creating the Page to the Page
 * responded by the Link.
 * <p/>
 * <pre>
 * add(new TypedLink&lt;MyObject&gt;(&quot;link&quot;, listItem.getModel(), Type.Primary )
 * {
 *     public void onClick()
 *     {
 *         MyObject obj = getModelObject();
 *         setResponsePage(new MyPage(obj));
 *     }
 * </pre>
 *
 * @author miha
 */
public abstract class TypedLink<T> extends Link<T> implements IBootstrapButton<TypedLink<T>> {

    private final Icon icon;
    private final Label label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     *
     * @param id    the components id
     * @param model mandatory parameter
     */
    public TypedLink(final String id, final IModel<T> model) {
        this(id, model, Buttons.Type.Link);
    }

    /**
     * Construct.
     *
     * @param id         the components id
     * @param type the type of the button
     */
    public TypedLink(final String id, final Buttons.Type type) {
        this(id, null, type);
    }

    /**
     * Construct.
     *
     * @param id         The component id
     * @param model      mandatory parameter
     * @param type the type of the button
     */
    public TypedLink(final String id, final IModel<T> model, final Buttons.Type type) {
        super(id, model);

        add(buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium));
        add(icon = new Icon("icon", IconType.NULL).invert());
        add(splitter = new WebMarkupContainer("splitter"));

        this.label = new Label("label", model);
        this.label.setRenderBodyOnly(true);
        add(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType());
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public TypedLink<T> setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public TypedLink<T> setIconType(IconType iconType) {
        icon.setType(iconType);

        return this;
    }

    /**
     * sets the size of the button
     *
     * @param size The button size
     * @return this instance for chaining
     */
    public TypedLink<T> setSize(Buttons.Size size) {
        buttonBehavior.withSize(size);

        return this;
    }

    /**
     * Sets the type of the button
     *
     * @param type The type of the button
     * @return this instance for chaining
     */
    public TypedLink<T> setType(Buttons.Type type) {
        this.buttonBehavior.withType(type);

        return this;
    }

    /**
     * inverts the icon color
     *
     * @param inverted true, if inverted version should be used
     * @return this instance for chaining
     */
    @Override
    public TypedLink<T> setInverted(final boolean inverted) {
        icon.setInverted(inverted);
        return this;
    }
}
