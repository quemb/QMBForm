package com.quemb.qmbform;

import com.quemb.qmbform.annotation.FormDescriptorAnnotationFactory;
import com.quemb.qmbform.annotation.FormElement;
import com.quemb.qmbform.annotation.FormElementDelegate;
import com.quemb.qmbform.annotation.FormOptionsObjectElement;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by pmaccamp on 9/14/2015.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class AnnotationFormTest {
    private Activity activity;
    private TestUserClass testUserClass;

    public class TestUserClass implements FormElementDelegate {
        @FormElement(
                rowDescriptorType = RowDescriptor.FormRowDescriptorTypeInteger,
                required = true,
                section = android.R.string.unknownName
        )
        public int age;

        @FormElement(
                rowDescriptorType = RowDescriptor.FormRowDescriptorTypeText,
                required = true,
                section = android.R.string.unknownName
        )
        public String name;

        @FormElement(
                rowDescriptorType = RowDescriptor.FormRowDescriptorTypeText,
                section = android.R.string.untitled
        )
        public String option;


        public TestUserClass(int age, String name, int bodyfat) {
            this.age = age;
            this.name = name;
        }

        @Override
        public boolean shouldAddRowDescriptorForField(RowDescriptor rowDescriptor, Field field) {

            if (rowDescriptor.getTag().equals("option")){
                return this.option != null;
            }

            return true;
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
    }

    @Test
    public void shouldNotIncludeSection() {

        FormDescriptorAnnotationFactory factory = new FormDescriptorAnnotationFactory(activity);
        FormDescriptor formDescriptor = factory.createFormDescriptorFromAnnotatedClass(testUserClass);

        assertThat(formDescriptor.countOfSections(), is(1));
        assertThat(formDescriptor.findRowDescriptor("option"), nullValue());

    }

    @Test
    public void shouldIncludeSection() {

        FormDescriptorAnnotationFactory factory = new FormDescriptorAnnotationFactory(activity);
        testUserClass.option = "mock";
        FormDescriptor formDescriptor = factory.createFormDescriptorFromAnnotatedClass(testUserClass);

        assertThat(formDescriptor.countOfSections(), is(2));
        assertThat(formDescriptor.findRowDescriptor("option"), notNullValue());

    }

    @After
    public void tearDown() {

    }

}
