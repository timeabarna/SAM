package com.reaktorlabs.sam.repository.model.samuser;

import com.reaktorlabs.sam.repository.model.asset.Asset;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SamUser.class)
public abstract class SamUser_ {

	public static volatile SingularAttribute<SamUser, String> firstName;
	public static volatile SingularAttribute<SamUser, String> lastName;
	public static volatile SingularAttribute<SamUser, Boolean> temporaryPassword;
	public static volatile SingularAttribute<SamUser, String> password;
	public static volatile ListAttribute<SamUser, Asset> assets;
	public static volatile SingularAttribute<SamUser, Long> id;
	public static volatile SingularAttribute<SamUser, SamUserRoleEnum> userRole;
	public static volatile SingularAttribute<SamUser, String> email;

}

