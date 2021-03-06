package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.abstract_classes.Documented;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Interaction extends Documented {

	private Set<URI> type;
	private HashMap<URI, Participation> participations;
	
	/**
	 * 
	 * @param identity an identity for the interaction	 
	 * @param type a type for the interaction
	 * @param participations a collection of participations for the interaction
	 */
	public Interaction(URI identity, Set<URI> type, List<Participation> participations) {
		super(identity);
		setType(type);
		this.participations = new HashMap<URI, Participation>(); 
		setParticipations(participations);
	}
	
	
	/**
	 * Adds the specified element to the set <code>type</code> if it is not already present. 
	 * @param typeURI
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addType(URI typeURI) {
		return type.add(typeURI);
	}
	
	/**
	 * Removes the specified element from the set <code>type</code> if it is present.
	 * @param typeURI
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeType(URI typeURI) {
		return type.remove(typeURI);
	}
	
	/**
	 * Sets the field variable <code>type</code> to the specified element.
	 * @param type
	 */
	public void setType(Set<URI> type) {
		this.type = type;
	}
	
	/**
	 * Returns the field variable <code>type</code>.
	 * @return
	 */
	public Set<URI> getType() {
		return type;
	}
	
	/**
	 * Returns true if the set <code>type</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsType(URI typeURI) {
		return type.contains(typeURI);
	}
	
	/**
	 * Removes all entries of the list of <code>type</code> instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearType() {
		type.clear();
	}
	
	/**
	 * Test if the optional field variable <code>participations</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetParticipations() {
		if (participations == null || participations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Calls the Participation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Participation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the  created Participation instance. 
	 */
	public Participation createParticipation(URI identity, Set<URI> role, URI participant) {
		Participation participation = new Participation(identity, role, participant);
		addParticipation(participation);
		return participation;
	}
	
	/**
	 * Adds the specified instance to the list of participations. 
	 * @param participation
	 */
	public void addParticipation(Participation participation) {
		// TODO: @addParticipation, Check for duplicated entries.
		
		participations.put(participation.getIdentity(), participation);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of participations if present.
	 * @param participationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Participation removeParticipation(URI participationURI) {
		// TODO: Need to prevent removing all participations here?
		return participations.remove(participationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of participations if present.
	 * @param participationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Participation getParticipation(URI participationURI) {
		return participations.get(participationURI);
	}
	
	/**
	 * Returns the list of participation instances owned by this instance. 
	 * @return the list of participation instances owned by this instance.
	 */
	public List<Participation> getParticipations() {
//		return (List<Participation>) participations.values();
		return new ArrayList<Participation>(participations.values());
	}
	
	/**
	 * Removes all entries of the list of participation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearParticipations() {
		Object[] keySetArray = participations.keySet().toArray();
		for (Object key : keySetArray) {
			removeParticipation((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of participation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param participations
	 */
	public void setParticipations(
			List<Participation> participations) {
		if(isSetParticipations())
			clearParticipations();	
		for (Participation participation : participations) {
			addParticipation(participation);
		}
	}
	
	



}
