package com.reaktorlabs.sam.repository.model.samuser;

import java.lang.annotation.Annotation;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SamUserTest {

    private Validator validator;

    @Before
    public void init() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testSamUserNotNullValidation() {
        final SamUser user = new SamUser();
        final Set<ConstraintViolation<SamUser>> violations = this.validator.validate(user);
        assertEquals("it should have 5", 5, violations.size());
    }

    @Test
    public void testSamUserNamValidation() {
        final SamUser userWithName = new SamUser();
        userWithName.setFirstName("a");
        userWithName.setLastName("b");
        userWithName.setEmail("aladar@aladar.com");
        userWithName.setPassword("password");
        userWithName.setUserRole(SamUserRoleEnum.STORE);

        final Set<ConstraintViolation<SamUser>> violations = this.validator.validate(userWithName);

        Annotation annotation
                = violations.iterator().
                        next().getConstraintDescriptor().
                        getAnnotation();

        assertEquals(Size.class, annotation.annotationType());
        assertEquals(2, ((Size) annotation).min());
        assertEquals(50, ((Size) annotation).max());
        assertEquals("it should have 2", 2, violations.size());
    }

}