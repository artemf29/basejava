package com.core.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
abstract public class Section implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
