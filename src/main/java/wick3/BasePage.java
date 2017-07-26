package wick3;

import org.apache.wicket.markup.html.basic.Label;

/**
 * A base page accessible by everybody - no authorization required.
 *
 * @author almaw
 */
public class BasePage extends WicketExamplePage
{
    public BasePage(){
        MyAuthenticatedWebSession s = (MyAuthenticatedWebSession) getSession();
        String userName = s.getUserName();
        add(new Label("uname", "Hello username, " + userName));
    }

}