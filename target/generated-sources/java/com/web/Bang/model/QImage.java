package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = 782908743L;

    public static final QImage image = new QImage("image");

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath originFileName = createString("originFileName");

    public QImage(String variable) {
        super(Image.class, forVariable(variable));
    }

    public QImage(Path<? extends Image> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImage(PathMetadata metadata) {
        super(Image.class, metadata);
    }

}

