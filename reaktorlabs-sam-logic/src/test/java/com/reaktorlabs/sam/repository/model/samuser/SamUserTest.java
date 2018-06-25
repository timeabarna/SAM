package com.reaktorlabs.sam.repository.model.samuser;

import java.lang.annotation.Annotation;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
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
    public void testSamUserNameValidation() {
        final SamUser userWithName = new SamUser();
        userWithName.setFirstName("a");
        userWithName.setLastName("b");
        userWithName.setEmail("john@doe.com");
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

    @Test
    public void testSamUserEmailValidation() {
        final SamUser userWithEmail = new SamUser();
        userWithEmail.setFirstName("John");
        userWithEmail.setLastName("Doe");
        userWithEmail.setEmail("doe.com");
        userWithEmail.setPassword("password");
        userWithEmail.setUserRole(SamUserRoleEnum.STORE);

        final Set<ConstraintViolation<SamUser>> violations = this.validator.validate(userWithEmail);
        
        Annotation annotation
                = violations.iterator().
                        next().getConstraintDescriptor().
                        getAnnotation();

        assertEquals(Pattern.class, annotation.annotationType());
        assertEquals("it should have 1", 1, violations.size());
    }
}