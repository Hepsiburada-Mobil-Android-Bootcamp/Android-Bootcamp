package com.android.camp.data.product

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class Error(
    @XmlElement var Code: String? = null,
    @XmlElement var Message: String? = null,
    @XmlElement var RequestId: String? = null,
    @XmlElement var HostId: String? = null
)
