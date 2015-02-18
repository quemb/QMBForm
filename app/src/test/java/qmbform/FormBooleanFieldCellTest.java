package qmbform;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.view.FormBooleanFieldCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by tonimoeckel on 02.09.14.
 */
@RunWith(RobolectricTestRunner.class)
public class FormBooleanFieldCellTest {


    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void shouldBeDisabled(){

        RowDescriptor rowDescriptor = RowDescriptor.newInstance("disabled", RowDescriptor.FormRowDescriptorTypeBooleanSwitch);
        rowDescriptor.setDisabled(true);

        FormBooleanFieldCell testCell = new FormBooleanFieldCell(activity, rowDescriptor);

        assertThat(testCell.getSwitch().isEnabled(), is(false));

    }

    @After
    public void tearDown() {

    }

}
