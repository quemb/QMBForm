package com.quemb.qmbform;

import com.quemb.qmbform.annotation.FormDescriptorAnnotationFactory;
import com.quemb.qmbform.annotation.FormElement;
import com.quemb.qmbform.annotation.FormOptionsObjectElement;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.widget.ListView;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pmaccamp on 9/14/2015.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class AnnotationFormTest {
    private Activity activity;
    private TestUserClass testUserClass;

    public class TestUserClass {
        @FormElement(
                rowDescriptorType = RowDescriptor.FormRowDescriptorTypeInteger,
                required = true
        )
        public int age;

        @FormElement(
                rowDescriptorType = RowDescriptor.FormRowDescriptorTypeText,
                required = true
        )
        public String name;

        @FormElement(
                rowDescriptorType = RowDescriptor.FormRowDescriptorTypeSelectorSegmentedControl,
                required = true,
                selectorOptions = {
                        @FormOptionsObjectElement(value = "10",
                                valueType = FormOptionsObjectElement.ValueTypes.INT,
                                displayText = "Low"),
                        @FormOptionsObjectElement(value = "20",
                                valueType = FormOptionsObjectElement.ValueTypes.INT,
                                displayText = "Medium"),
                        @FormOptionsObjectElement(value = "30",
                                valueType = FormOptionsObjectElement.ValueTypes.INT,
                                displayText = "High")
                }
        )
        public int bodyfat;

        public TestUserClass(int age, String name, int bodyfat) {
            this.age = age;
            this.name = name;
            this.bodyfat = bodyfat;
        }
    }

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
        testUserClass = new TestUserClass(25, "John", 10);
    }

    @Test
    public void hasCorrectFormValues() {
        FormDescriptorAnnotationFactory factory = new FormDescriptorAnnotationFactory(activity);
        FormDescriptor formDescriptor = factory.createFormDescriptorFromAnnotatedClass(testUserClass);

        final FormManager formManager = new FormManager();
        ListView listView = new ListView(activity);
        formManager.setup(formDescriptor, listView, activity);

        Map formValues = formDescriptor.getFormValues();

        assertThat((int) formValues.get("age"), is(25));
        assertThat((String) formValues.get("name"), is("John"));
        assertThat((int) formValues.get("bodyfat"), is(10));
    }

    @After
    public void tearDown() {

    }

}
