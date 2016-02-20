package com.dexesttp.hkxpack.tagxml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.dexesttp.hkxpack.data.HKXData;
import com.dexesttp.hkxpack.data.HKXObject;
import com.dexesttp.hkxpack.data.members.HKXMember;
import com.dexesttp.hkxpack.l10n.SBundle;

/**
 * Creates {@link Node} from {@link HKXData}.
 */
class TagXMLDataCreator {
	private final Document document;
	private final TagXMLMemberCreator memberCreator;
	private TagXMLObjectCreator objectCreator;

	/**
	 * Initialize the {@link TagXMLDataCreator}, linking it definitely to a {@link Document}.
	 * @param document
	 */
	TagXMLDataCreator(Document document) {
		this.document = document;
		this.memberCreator = new TagXMLMemberCreator(this);
		this.objectCreator = new TagXMLObjectCreator(this);
	}

	/**
	 * Creates a {@link Node} from a {@link HKXData} component.
	 * @param content the {@link HKXData} to convert.
	 * @return a {@link Node} containing the data.
	 */
	Node create(HKXData content) {
		if(content instanceof HKXObject)
			return objectCreator.create((HKXObject) content);
		if(content instanceof HKXMember)
			return memberCreator.create((HKXMember) content);
		throw new IllegalArgumentException(SBundle.getString("error.tag.create.type.unknown") + "[#060]");
	}

	/**
	 * Retrieves the specialized {@link TagXMLMemberCreator}.
	 * @return the embedded {@link TagXMLMemberCreator}.
	 */
	TagXMLMemberCreator memberCreator() {
		return memberCreator;
	}

	/**
	 * Retrieves the linked {@link Document}
	 * @return the linked {@link Document}
	 */
	Document document() {
		return document;
	}
}
