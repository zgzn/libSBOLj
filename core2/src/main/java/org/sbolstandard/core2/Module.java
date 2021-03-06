package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Documented;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Module extends Documented {
	
	private HashMap<URI, MapsTo> mappings;
	private URI definition;
	
	public Module(URI identity, URI instantiatedModule) {
		super(identity);
		setDefinition(instantiatedModule);
		this.mappings = new HashMap<URI, MapsTo>();
	}

	
	/**
	 * Returns field variable <code>instantiatedModule</code>.
	 * @return field variable <code>instantiatedModule</code>
	 */
	public URI getDefinition() {
		return definition;
	}

	/**
	 * Sets field variable <code>instantiatedModule</code> to the specified element.
	 * @param definitionURI
	 */
	public void setDefinition(URI definitionURI) {
		this.definition = definitionURI;
	}

	/**
	 * Test if optional field variable <code>references</code> is set.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean isSetMappings() {
		if (mappings.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 * @param reference
	 */
	public void addMapping(MapsTo reference) {
		// TODO: @addReference, Check for duplicated entries.
		mappings.put(reference.getIdentity(), reference);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of references if present.
	 * @param referenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo removeMapping(URI referenceURI) {
		return mappings.remove(referenceURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of references if present.
	 * @param referenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getMapping(URI referenceURI) {
		return mappings.get(referenceURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance.
	 * @return the list of reference instances owned by this instance.
	 */
	public List<MapsTo> getMappings() {
//		return (List<MapsTo>) references.values();
		return new ArrayList<MapsTo>(mappings.values());
	}
	
	/**
	 * Removes all entries of the list of reference instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearMappings() {
		Object[] keySetArray = mappings.keySet().toArray();
		for (Object key : keySetArray) {
			removeMapping((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param mappings
	 */
	public void setMappings(
			List<MapsTo> mappings) {
		clearMappings();		
		for (MapsTo mapping : mappings) {
			addMapping(mapping);
		}
	}
	
//	/**
//	 * Set optional field variable <code>references</code> to an empty list.
//	 */
//	public void unsetReferences() {
//		references.clear();
//	}

}
