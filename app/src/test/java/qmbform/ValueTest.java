package qmbform;

import android.app.Activity;
import android.widget.ListView;

import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.OnValueChangeListener;
import com.quemb.qmbform.descriptor.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by tonimoeckel on 28.08.14.
 */
@RunWith(RobolectricTestRunner.class)
public class ValueTest {

    private Value<Integer> value;
    private Integer resultInt = 0;

    @Before
    public void setUp() {
        value = new Value<Integer>(50);
    }

    @Test
    public void shouldCallListener() {

        OnValueChangeListener<Integer> listener = new OnValueChangeListener<Integer>() {
            @Override
            public void onChange(Integer value) {
                resultInt = value;
            }
        };
        value.setOnValueChangeListener(listener);
        value.setValue(100);

        assertThat(resultInt, is(100));

    }

    @After
    public void tearDown() {

    }

}