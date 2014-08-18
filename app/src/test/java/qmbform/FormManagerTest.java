import android.app.Activity;
import android.widget.ListView;
import com.quemb.qmbform.descriptor.FormDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import com.quemb.qmbform.FormManager;


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

        ListView listView = new ListView();
        FormDescriptor formDescriptor = new FormDescriptor();
        formManager.setup(formDescriptor, listView, activity);

        assertThat(listView.getAdapter(), is(notNullValue()));

    }

    @After
    public void tearDown() {

    }

}