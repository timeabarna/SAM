package com.reaktorlabs.sam.repository.model.asset;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class AssetTest {

    private Validator validator;

    @Before
    public void init() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testAssetNotNullValidation() {
        final Asset asset = new Asset();
        final Set<ConstraintViolation<Asset>> violations = this.validator.validate(asset);
        assertEquals("it should have 8", 8, violations.size());
    }

    @Test
    public void testAssetSizeValidation() throws ParseException {
        final Asset testAsset = new Asset();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final Date endSupportDate = dateFormat.parse("01-01-2000");
        
        testAsset.setAssetName("Mo");
        testAsset.setAssetTag("MON-1234");
        testAsset.setLocation("h");
        testAsset.setManufacturer("C");
        testAsset.setModel("E");
        testAsset.setSerialNumber("123");
        testAsset.setStatus(AssetStatusEnum.STORED);
        testAsset.setSupportEndDate(endSupportDate);

        final Set<ConstraintViolation<Asset>> violations = this.validator.validate(testAsset);

        Annotation annotation
                = violations.iterator().
                        next().getConstraintDescriptor().
                        getAnnotation();

        assertEquals(Size.class, annotation.annotationType());
        assertEquals("it should have 5", 5, violations.size());
    }

    @Test
    public void testAssetTagValidation() throws ParseException {
        final Asset assetWithAssetTag = new Asset();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final Date endSupportDate = dateFormat.parse("01-01-2000");
        
        assetWithAssetTag.setAssetName("Monitor");
        assetWithAssetTag.setAssetTag("tag");
        assetWithAssetTag.setLocation("here");
        assetWithAssetTag.setManufacturer("Company");
        assetWithAssetTag.setModel("EQ");
        assetWithAssetTag.setSerialNumber("123456");
        assetWithAssetTag.setStatus(AssetStatusEnum.STORED);
        assetWithAssetTag.setSupportEndDate(endSupportDate);

        final Set<ConstraintViolation<Asset>> tagViolations = this.validator.validate(assetWithAssetTag);

        Annotation annotation
                = tagViolations.iterator().
                        next().getConstraintDescriptor().
                        getAnnotation();

        assertEquals(Pattern.class, annotation.annotationType());
        assertEquals("it should have 1", 1, tagViolations.size());
    }
}
