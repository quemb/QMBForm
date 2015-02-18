package qmbform;

import android.app.Activity;
import android.widget.ListView;
import com.quemb.qmbform.descriptor.FormDescriptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import com.quemb.qmbform.descriptor.FormManager;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Created by tonimoeckel on 12.08.14.
 */
@RunWith(RobolectricTestRunner.class)
public class FormManagerTest {

    private FormManager formManager;
    private Activity activity;

    @Before
    public void setUp() {
        formManager = new FormManager();
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void shouldSetupListView(){

        ListView listView = new ListView(activity);
        FormDescriptor formDescriptor = new FormDescriptor();
        formManager.setup(formDescriptor, listView, activity);

        assertThat(listView.getAdapter(), is(notNullValue()));

    }

    @After
    public void tearDown() {

    }

}