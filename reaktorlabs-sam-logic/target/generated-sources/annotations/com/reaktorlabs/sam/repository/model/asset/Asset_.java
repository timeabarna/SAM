package com.reaktorlabs.sam.repository.model.asset;

import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Asset.class)
public abstract class Asset_ {

	public static volatile SingularAttribute<Asset, SamUser> owner;
	public static volatile SingularAttribute<Asset, Date> supportEndDate;
	public static volatile SingularAttribute<Asset, String> serialNumber;
	public static volatile SingularAttribute<Asset, String> assetName;
	public static volatile SingularAttribute<Asset, String> model;
	public static volatile SingularAttribute<Asset, String> location;
	public static volatile SingularAttribute<Asset, Long> id;
	public static volatile SingularAttribute<Asset, String> assetTag;
	public static volatile SingularAttribute<Asset, String> manufacturer;
	public static volatile SingularAttribute<Asset, AssetStatusEnum> status;

}

