package org.sbolstandard.core2.test;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.*; 
import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.Identified;
import org.sbolstandard.core2.abstract_classes.Location;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;

public class writeTester {
	
	/*
	 * TODO: rename getData() to getIdentificationData()
	 * add timestamp to getData()
	 */
	public static void main( String[] args ) throws Exception
	{
		SBOLDocument SBOL2Doc_test = new SBOLDocument(); 
		 
		Collection myParts = createCollection(SBOL2Doc_test,
				getData("myParts_1","myParts_persistentIdentity","v1.0","myParts","myParts", "myParts")); 
		
		myParts.addMember(get_pLac(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_tetR(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_pLactetR(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_LacI(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_TetR(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_LacI_Inv(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_pLacSeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_tetRSeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_pLactetRSeq(SBOL2Doc_test).getIdentity());
		
//		get_LacI_Inv (SBOL2Doc_test);
//		createLacI_Inverter(SBOL2Doc_test);
//		createTetR_Inverter(SBOL2Doc_test);
		
		SBOLWriter.write(SBOL2Doc_test,(System.out));
	}
	
	private static Structure get_pLacSeq (SBOLDocument SBOL2Doc_test)
	{
		return createStructureData(SBOL2Doc_test, 
				getData("pLacSeq_1","pLacSeq_persistentIdentity","v1.0","pLacSeq","pLacSeq", "pLacSeq", "pLacSeq_element", "pLacSeq_encoding")); 
	}
	
	private static Structure get_tetRSeq (SBOLDocument SBOL2Doc_test)
	{
		return createStructureData(SBOL2Doc_test, 
				getData("tetRSeq_1","tetRSeq_persistentIdentity","v1.0","tetRSeq","tetRSeq", "tetRSeq", "tetRSeq_element", "tetRSeq_encoding")); 
	}
	
	private static Structure get_pLactetRSeq (SBOLDocument SBOL2Doc_test)
	{
		return createStructureData(SBOL2Doc_test, 
				getData("pLactetRSeq_1","pLactetRSeq_persistentIdentity","v1.0","pLactetRSeq","pLactetRSeq", "pLactetRSeq", "pLactetRSeq_element", "pLactetRSeq_encoding")); 
	}
	
	private static Component get_pLac (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Promoter"),
				getData("pLac_1","pLac_persistentIdentity","v1.0","pLac","pLac", "pLac"),
				get_pLacSeq(SBOL2Doc_test), 
				null, null, null); 	
	}
	
	private static Component get_tetR (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("CDS"),
				getData("tetR_1","tetR_persistentIdentity","v1.0","tetR","tetR", "tetR"),
				get_tetRSeq(SBOL2Doc_test), 
				null, null, null);	
	}
	
	private static StructuralInstantiation get_P (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralInstantiationData(
				getData("P_1", "P_persistentIdentity", "P_v1.0", "P", "P", "P", "public"), 
				get_pLac(SBOL2Doc_test));
	}
	
	private static StructuralInstantiation get_C (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralInstantiationData(
				getData("C_1", "C_persistentIdentity", "C_v1.0", "C", "C", "C", "public"), 
				get_tetR(SBOL2Doc_test));
	}
	
	private static StructuralAnnotation get_p_structAnnotate (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralAnnotationData(
				getData("p_structAnnotate_1", "p_structAnnotate_persistentIdentity", "v1.0", "p_structAnnotate", "p_structAnnotate", "p_structAnnotate"), 
				get_P(SBOL2Doc_test), 
				0, 10);
	}
	
	private static StructuralAnnotation get_c_structAnnotate (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralAnnotationData(
				getData("p_structAnnotate_1", "p_structAnnotate_persistentIdentity", "v1.0", "p_structAnnotate", "p_structAnnotate", "p_structAnnotate"), 
				get_P(SBOL2Doc_test), 
				0, 10);
	}
	
	private static StructuralConstraint get_struct_constraint (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralConstraintData(
				getData("struct_constraint_1", "struct_constraint_persistentIdentity", "v1.0", "precedes"), 
				get_P(SBOL2Doc_test), 
				get_C(SBOL2Doc_test));
	}
	
	private static Component get_pLactetR (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Gene"),
				getData("pLactetR_1","pLactetR_persistentIdentity","v1.0","pLactetR","pLactetR", "pLactetR"),
				get_pLactetRSeq(SBOL2Doc_test), 
				getStructuralInstantiation_List(get_P(SBOL2Doc_test), get_C(SBOL2Doc_test)), 
				null,  
				getStructuralConstraint_List(get_struct_constraint(SBOL2Doc_test))); 
	}
	
	private static Component get_LacI (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein"),
				getSetOfURI("Transcriptionfactor"),
				getData("LacI_1","LacI_persistentIdentity","v1.0","LacI", "LacI", "LacI"),
				null, 
				null, null, null); 
	}
	
	private static Component get_TetR (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein"),
				getSetOfURI("Transcriptionfactor."),
				getData("TetR_1","TetR_persistentIdentity","v1.0","TetR", "TetR", "TetR"),
				null, 
				null, null, null); 
	}
	
	private static FunctionalInstantiation get_LacIIn (SBOLDocument SBOL2Doc_test)
	{
		return createFunctionalInstantiationData(
				getData("LacIIn_1","LacIIn_persistentIdentity","v1.0","LacIIn","LacIIn", "LacIIn","public", "input"),
				get_LacI(SBOL2Doc_test)); 
	}
	
	private static FunctionalInstantiation get_TetROut (SBOLDocument SBOL2Doc_test)
	{
		return createFunctionalInstantiationData(
				getData("TetROut_1","TetROut_persistentIdentity","v1.0","TetROut","TetROut", "TetROut","public", "output"),
				get_TetR(SBOL2Doc_test));
	}
	
	private static FunctionalInstantiation get_LacIInv (SBOLDocument SBOL2Doc_test)
	{
		return createFunctionalInstantiationData(
				getData("LacIInv_1","LacIInv_persistentIdentity","v1.0","LacInv","LacIInv", "LacIInv","private", "none"),
				get_pLactetR(SBOL2Doc_test));
	}
	
	private static Participation get_p1 (SBOLDocument SBOL2Doc_test, String identity)
	{
		return createParticipationData(
				getURI(identity), 
				getSetOfURI("repressor"), 
				get_LacIIn(SBOL2Doc_test));
	}
	
	private static Participation get_p2 (SBOLDocument SBOL2Doc_test, String identity )
	{
		return createParticipationData(
				getURI(identity), 
				getSetOfURI("repressed"), 
				get_LacIInv(SBOL2Doc_test));
	}
	
	private static Interaction get_interact1 (SBOLDocument SBOL2Doc_test)
	{
		return createInteractionData(
				getData("interact1_1","interact1_persistentIdentity","v1.0","interact1","interact1", "interact1"),
				getParticipation_List(get_p1(SBOL2Doc_test, "p1_1"), get_p2(SBOL2Doc_test, "p2_1")), 
				getSetOfURI("repression")); 
	}
	
	private static Interaction get_interact2 (SBOLDocument SBOL2Doc_test)
	{
		return createInteractionData(
				getData("interact2_1","interact2_persistentIdentity","v1.0","interact2","interact2", "interact2"),
				getParticipation_List(get_p1(SBOL2Doc_test,"p2__1"), get_p2(SBOL2Doc_test,"p2_1")), 
				getSetOfURI("produces")); 
	}
	
	private static Module get_LacI_Inv (SBOLDocument SBOL2Doc_test)
	{
		return createModuleData(SBOL2Doc_test, 
				getSetOfURI("Inverter"),
				getSetOfURI("Transcriptionfactor."),
				getData("LacI_Inv_1","LacI_Inv_persistentIdentity","v1.0","LacI_Inv","LacI_Inv", "LacI_Inv"),
				getFunctionalInstantiation_List(
						get_LacIIn(SBOL2Doc_test), 
						get_TetROut(SBOL2Doc_test), 
						get_LacIInv(SBOL2Doc_test)), 
				getInteraction_List(
						get_interact1(SBOL2Doc_test), 
						get_interact2(SBOL2Doc_test)), 
				null, null
				);
	}
	
	
	// ------------------------------ CREATING TetR_Inverter ------------------------------
	private static Structure get_ptetSeq (SBOLDocument SBOL2Doc_test)
	{
		return createStructureData(SBOL2Doc_test, 
				getData("ptetSeq_1","ptetSeq_persistentIdentity","v1.0","ptetSeq","ptetSeq", "ptetSeq", "ptetSeq_element", "ptetSeq_encoding")); 
	}
	
	private static Structure get_lacISeq (SBOLDocument SBOL2Doc_test)
	{
		return createStructureData(SBOL2Doc_test, 
				getData("lacISeq_1","lacISeq_persistentIdentity","v1.0","lacISeq","lacISeq", "lacISeq", "lacISeq_element", "lacISeq_encoding")); 
	}
	
	private static Structure get_ptetlacISeq (SBOLDocument SBOL2Doc_test)
	{
		return createStructureData(SBOL2Doc_test, 
				getData("ptetlacISeq_1","ptetlacISeq_persistentIdentity","v1.0","ptetlacISeq","ptetlacISeq", "ptetlacISeq", "ptetlacISeq_element", "ptetlacISeq_encoding")); 
	}
	
	private static Component get_ptet(SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Promoter"),
				getData("ptet_1","ptet_persistentIdentity","v1.0","ptet","ptet", "ptet"),
				get_ptetSeq(SBOL2Doc_test), 
				null, null, null);  	
	}
	
	private static Component get_lacI (SBOLDocument SBOL2Doc_test) 
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("CDS"),
				getData("lacI_1","lacI_persistentIdentity","v1.0","lacI","lacI", "lacI"),
				get_lacISeq(SBOL2Doc_test), 
				null, null, null);
	}
	
	private static StructuralInstantiation get_P2 (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralInstantiationData(
				getData("P2_1", "P2_persistentIdentity", "P2_v1.0", "P2", "P2", "P2", "public"), 
				get_ptet(SBOL2Doc_test));
	}
	
	private static StructuralInstantiation get_C2 (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralInstantiationData(
				getData("C2_1", "C2_persistentIdentity", "C2_v1.0", "C2", "C2", "C2", "public"), 
				get_lacI(SBOL2Doc_test));
	}
	
	private static StructuralAnnotation get_p2_structAnnotate (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralAnnotationData(
				getData("p2_structAnnotate_1", "p2_structAnnotate_persistentIdentity", "v1.0", "p2_structAnnotate", "p2_structAnnotate", "p2_structAnnotate"), 
				get_P2(SBOL2Doc_test), 
				0, 10);
	}
	
	private static StructuralAnnotation get_c2_structAnnotate (SBOLDocument SBOL2Doc_test)
	{
		return createStructuralAnnotationData(
				getData("c2_structAnnotate_1", "c2_structAnnotate_persistentIdentity", "v1.0", "c2_structAnnotate", "c2_structAnnotate", "c2_structAnnotate"), 
				get_C2(SBOL2Doc_test), 
				0, 10);
	}
	
	private static Component get_ptetlacI (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Gene"),
				getData("ptetlacI_1","ptetlacI_persistentIdentity","v1.0","ptetlacI","ptetlacI", "ptetlacI"),
				get_ptetlacISeq(SBOL2Doc_test), 
				getStructuralInstantiation_List(get_P2(SBOL2Doc_test), get_C2(SBOL2Doc_test)), 
				getStructuralAnnotation_List(get_p2_structAnnotate(SBOL2Doc_test), get_c2_structAnnotate(SBOL2Doc_test)), 
				null); 
	}
	
	private static FunctionalInstantiation get_TetRIn (SBOLDocument SBOL2Doc_test)
	{
		return createFunctionalInstantiationData(
				getData("TetRIn_1","TetRIn_persistentIdentity","v1.0","TetRIn","TetRIn", "TetRIn","public", "input"),
				get_TetR(SBOL2Doc_test)); 
	}
	
	private static FunctionalInstantiation get_LacIOut (SBOLDocument SBOL2Doc_test)
	{
		return createFunctionalInstantiationData(
				getData("LacIOut_1","LacIOut_persistentIdentity","v1.0","LacIOut","LacIOut", "LacIOut","public", "output"),
				get_LacI(SBOL2Doc_test));
	}
	
	
	//TODO: to be completed
//	private static Module get_TetR_Inv (SBOLDocument SBOL2Doc_test)
//	{
//		return createModuleData(SBOL2Doc_test, 
//				getSetOfURI("Inverter"),
//				getSetOfURI("Transcriptionfactor."),
//				getData("TetR_Inv_1","TetR_Inv_persistentIdentity","v1.0","TetR_Inv","TetR_Inv", "TetR_Inv"),
//				getFunctionalInstantiation_List(
//						get_LacIIn(SBOL2Doc_test), 
//						get_TetROut(SBOL2Doc_test), 
//						get_LacInv(SBOL2Doc_test)), 
//				getInteraction_List(
//						get_interact1(SBOL2Doc_test), 
//						get_interact2(SBOL2Doc_test)), 
//				null, null
//				);
//	}
	
	private static void setCommonTopLevelData (TopLevel t, URI identity, URI persistentIdentity,
			String version, String displayId, String name, String description)
	{
		setCommonDocumentedData(t, identity, persistentIdentity, version, displayId, name, description);
	}
	
	private static void setCommonDocumentedData (Documented d, URI identity, URI persistentIdentity,
			String version, String displayId, String name, String description)
	{
		d.setDisplayId(displayId);
		d.setName(name);
		d.setDescription(description);
		
		setCommonIdentifiedData(d, identity, persistentIdentity, version);
	}
	
	private static void setCommonIdentifiedData (Identified i, URI identity, URI persistentIdentity,
			String version)
	{
		i.setIdentity(identity);
		i.setPersistentIdentity(persistentIdentity);
		i.setVersion(version);
//		i.setTimeStamp(timeStamp);
	}
	
	private static void createLacI_Inverter(SBOLDocument SBOL2Doc_test)
	{
		Structure pLacSeq = createStructureData(SBOL2Doc_test, 
				getData("pLacSeq_1","pLacSeq_persistentIdentity","v1.0","pLacSeq","pLacSeq", "pLacSeq", "pLacSeq_element", "pLacSeq_encoding")); 
		Structure tetRSeq = createStructureData(SBOL2Doc_test, 
				getData("tetRSeq_1","tetRSeq_persistentIdentity","v1.0","tetRSeq","tetRSeq", "tetRSeq", "tetRSeq_element", "tetRSeq_encoding")); 
		Structure pLactetRSeq = createStructureData(SBOL2Doc_test, 
				getData("pLactetRSeq_1","pLactetRSeq_persistentIdentity","v1.0","pLactetRSeq","pLactetRSeq", "pLactetRSeq", "pLactetRSeq_element", "pLactetRSeq_encoding")); 
	
		Component pLac = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Promoter"),
				getData("pLac_1","pLac_persistentIdentity","v1.0","pLac","pLac", "pLac"),
				pLacSeq, 
				null, null, null); 
		
		Component tetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("CDS"),
				getData("tetR_1","tetR_persistentIdentity","v1.0","tetR","tetR", "tetR"),
				tetRSeq, 
				null, null, null);
		
		
		StructuralInstantiation P = createStructuralInstantiationData(
				getData("P_1", "P_persistentIdentity", "P_v1.0", "P", "P", "P", "public"), 
				pLac);
		
		StructuralInstantiation C = createStructuralInstantiationData(
				getData("C_1", "C_persistentIdentity", "C_v1.0", "C", "C", "C", "public"), 
				tetR);
		
		//NOTE: either use StructuralAnnotation OR StructuralConstraint. Never both 
		StructuralAnnotation p_structAnnotate  = createStructuralAnnotationData(
				getData("p_structAnnotate_1", "p_structAnnotate_persistentIdentity", "v1.0", "p_structAnnotate", "p_structAnnotate", "p_structAnnotate"), 
				P, 
				0, 10);
		
		StructuralAnnotation c_structAnnotate  = createStructuralAnnotationData(
				getData("c_structAnnotate_1", "c_structAnnotate_persistentIdentity", "v1.0", "c_structAnnotate", "c_structAnnotate", "c_structAnnotate"), 
				C, 
				11, 20); 
		
		StructuralConstraint struct_constraint = createStructuralConstraintData(
				getData("struct_constraint_1", "struct_constraint_persistentIdentity", "v1.0", "precedes"), 
				P, 
				C); 

		Component pLactetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Gene"),
				getData("pLactetR_1","pLactetR_persistentIdentity","v1.0","pLactetR","pLactetR", "pLactetR"),
				pLactetRSeq, 
				getStructuralInstantiation_List(P, C), 
				null, //getStructuralAnnotation_List(p_structAnnotate, c_structAnnotate), 
				getStructuralConstraint_List(struct_constraint)); 
		
		Component LacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein"),
				getSetOfURI("Transcriptionfactor"),
				getData("LacI_1","LacI_persistentIdentity","v1.0","LacI", "LacI", "LacI"),
				null, 
				null, null, null); 
		
		Component TetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein"),
				getSetOfURI("Transcriptionfactor."),
				getData("TetR_1","TetR_persistentIdentity","v1.0","TetR", "TetR", "TetR"),
				null, 
				null, null, null); 
		
		FunctionalInstantiation LacIIn = createFunctionalInstantiationData(
				getData("LacIIn_1","LacIIn_persistentIdentity","v1.0","LacIIn","LacIIn", "LacIIn","public", "input"),
				LacI);
		
		FunctionalInstantiation TetROut = createFunctionalInstantiationData(
				getData("TetROut_1","TetROut_persistentIdentity","v1.0","TetROut","TetROut", "TetROut","public", "output"),
				TetR);
		
		FunctionalInstantiation Inv = createFunctionalInstantiationData(
				getData("Inv_1","Inv_persistentIdentity","v1.0","Inv","Inv", "Inv","private", "none"),
				pLactetR);
		
		Participation p1 = createParticipationData(
				getURI("participation1_1"), 
				getSetOfURI("repressor"), 
				LacIIn);
		
		Participation p2 = createParticipationData(
				getURI("participation2_1"), 
				getSetOfURI("repressed"), 
				Inv);
		
		Interaction interact1 =  createInteractionData(
				getData("interact1_1","interact1_persistentIdentity","v1.0","interact1","interact1", "interact1"),
				getParticipation_List(p1, p2), 
				getSetOfURI("repression")); 
		
		Participation p3 = createParticipationData(
				getURI("participation3_1"), 
				getSetOfURI("producer"), 
				Inv);
		
		Participation p4 = createParticipationData(
				getURI("participation4_1"), 
				getSetOfURI("produced"), 
				TetROut);
		
		Interaction interact2 =  createInteractionData(
				getData("interact2_1","interact2_persistentIdentity","v1.0","interact2","interact2", "interact2"),
				getParticipation_List(p3, p4), 
				getSetOfURI("produces")); 
		
		Module LacI_Inv = createModuleData(SBOL2Doc_test, 
				getSetOfURI("Inverter"),
				getSetOfURI("Transcriptionfactor."),
				getData("LacI_Inv_1","LacI_Inv_persistentIdentity","v1.0","LacI_Inv","LacI_Inv", "LacI_Inv"),
				getFunctionalInstantiation_List(LacIIn, TetROut, Inv), 
				getInteraction_List(interact1, interact2), null, null
				);
		
		FunctionalInstantiation LacISp = createFunctionalInstantiationData(
				getData("LacISp_1","LacISp_persistentIdentity","v1.0","LacISp","LacISp", "LacISp","private", "none"),
				LacI);
		
		FunctionalInstantiation TetRSp = createFunctionalInstantiationData(
				getData("TetRSp_1","TetRSp_persistentIdentity","v1.0","TetRSp","TetRSp", "TetRSp","private", "none"),
				TetR);
		
		MapsTo LacISp_LacIIn = createMapTo(
				getURI("LacISp_Mapsto_LacIIn_1"), 
				getRefinement("merge"), 
				LacISp, LacIIn); 
		
		MapsTo TetRSp_TetROut = createMapTo(
				getURI("TetRSp_Mapsto_TetROut_1"), 
				getRefinement("merge"), 
				TetRSp, TetROut); 
		
		ModuleInstantiation Inv1 = createModuleInstantiationData(
				getData("Inv1_1","Inv1_persistentIdentity","v1.0","Inv1","Inv1", "Inv1"),
				LacI_Inv, 
				getMapsTo_List(LacISp_LacIIn, TetRSp_TetROut)); 
		
		Model Togglemodel = createModelData(SBOL2Doc_test, 
				getData("Togglemodel_1","Togglemodel_persistentIdentity","v1.0","Togglemodel","Togglemodel", "Togglemodel", "Togglemodel_source", "Togglemodel_language", "Togglemodel_framework"),
				getSetOfURI("Togglemodel")
				); 
		
		Module Toggle = createModuleData(SBOL2Doc_test, 
				getSetOfURI("Toggle"),
				getSetOfURI("Toggle"),
				getData("Toggle_1","Toggle_persistentIdentity","v1.0","Toggle","Toggle", "Toggle"),
				getFunctionalInstantiation_List(LacISp, TetRSp), 
				getInteraction_List(interact1, interact2), null, null
				);
	}
	
	private static void createTetR_Inverter(SBOLDocument SBOL2Doc_test)
	{
		Structure pLacSeq = createStructureData(SBOL2Doc_test, 
				getData("pLacSeq_1","pLacSeq_persistentIdentity","v1.0","pLacSeq","pLacSeq", "pLacSeq", "pLacSeq_element", "pLacSeq_encoding")); 
		Structure tetRSeq = createStructureData(SBOL2Doc_test, 
				getData("tetRSeq_1","tetRSeq_persistentIdentity","v1.0","tetRSeq","tetRSeq", "tetRSeq", "tetRSeq_element", "tetRSeq_encoding")); 
		Structure pLactetRSeq = createStructureData(SBOL2Doc_test, 
				getData("pLactetRSeq_1","pLactetRSeq_persistentIdentity","v1.0","pLactetRSeq","pLactetRSeq", "pLactetRSeq", "pLactetRSeq_element", "pLactetRSeq_encoding")); 
	
		Component ptet = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Promoter"),
				getData("ptet_1","ptet_persistentIdentity","v1.0","ptet","ptet", "ptet"),
				pLacSeq, 
				null, null, null); 
		
		Component lacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("CDS"),
				getData("lacI_1","lacI_persistentIdentity","v1.0","lacI","lacI", "lacI"),
				tetRSeq, 
				null, null, null);
		
		
		StructuralInstantiation P = createStructuralInstantiationData(
				getData("P_1", "P_persistentIdentity", "v1.0", "P", "P", "P", "public"), 
				ptet);
		
		StructuralInstantiation C = createStructuralInstantiationData(
				getData("C_1", "C_persistentIdentity", "v1.0", "C", "C", "C", "public"), 
				lacI);
		
		//NOTE: either use StructuralAnnotation OR StructuralConstraint. Never both 
		StructuralAnnotation p_structAnnotate  = createStructuralAnnotationData(
				getData("p_structAnnotate_1", "p_structAnnotate_persistentIdentity", "v1.0", "p_structAnnotate", "p_structAnnotate", "p_structAnnotate"), 
				P, 
				0, 10);
		
		StructuralAnnotation c_structAnnotate  = createStructuralAnnotationData(
				getData("c_structAnnotate_1", "c_structAnnotate_persistentIdentity", "v1.0", "c_structAnnotate", "c_structAnnotate", "c_structAnnotate"), 
				C, 
				11, 20); 
		
		StructuralConstraint struct_constraint = createStructuralConstraintData(
				getData("struct_constraint_1", "struct_constraint_persistentIdentity", "v1.0", "precedes"), 
				P, 
				C); 

		Component ptetLacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA"),
				getSetOfURI("Gene"),
				getData("ptetLacI_1","ptetLacI_persistentIdentity","v1.0","ptetLacI","ptetLacI", "ptetLacI"),
				pLactetRSeq, 
				getStructuralInstantiation_List(P, C), 
				null, //getStructuralAnnotation_List(p_structAnnotate, c_structAnnotate), 
				getStructuralConstraint_List(struct_constraint)); 
		
		Component TetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein"),
				getSetOfURI("Transcriptionfactor"),
				getData("TetR_1","TetR_persistentIdentity","v1.0","TetR", "TetR", "TetR"),
				null, 
				null, null, null); 
		
		Component LacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein"),
				getSetOfURI("Transcriptionfactor."),
				getData("LacI_1","LacI_persistentIdentity","v1.0","LacI", "LacI", "LacI"),
				null, 
				null, null, null); 
		
		FunctionalInstantiation TetRIn = createFunctionalInstantiationData(
				getData("TetRIn_1","TetRIn_persistentIdentity","v1.0","TetRIn","TetRIn", "TetRIn","public", "input"),
				TetR);
		
		FunctionalInstantiation LacIOut = createFunctionalInstantiationData(
				getData("LacIOut_1","LacIOut_persistentIdentity","v1.0","LacIOut","LacIOut", "LacIOut","public", "output"),
				LacI);
		
		FunctionalInstantiation Inv = createFunctionalInstantiationData(
				getData("Inv_1","Inv_persistentIdentity","v1.0","Inv","Inv", "Inv","private", "none"),
				ptetLacI);
		
		Participation p1 = createParticipationData(
				getURI("participation1_1"), 
				getSetOfURI("repressor"), 
				TetRIn);
		
		Participation p2 = createParticipationData(
				getURI("participation2_1"), 
				getSetOfURI("repressed"), 
				Inv);
		
		Interaction interact1 =  createInteractionData(
				getData("interact1_1","interact1_persistentIdentity","v1.0","interact1","interact1", "interact1"),
				getParticipation_List(p1, p2), 
				getSetOfURI("repression")); 
		
		Participation p3 = createParticipationData(
				getURI("participation3_1"), 
				getSetOfURI("producer"), 
				Inv);
		
		Participation p4 = createParticipationData(
				getURI("participation4_1"), 
				getSetOfURI("produced"), 
				LacIOut);
		
		Interaction interact2 =  createInteractionData(
				getData("interact2_1","interact2_persistentIdentity","v1.0","interact2","interact2", "interact2"),
				getParticipation_List(p3, p4), 
				getSetOfURI("produces")); 
		
		Module LacI_Inv = createModuleData(SBOL2Doc_test, 
				getSetOfURI("Inverter"),
				getSetOfURI("Transcriptionfactor."),
				getData("LacI_Inv_1","LacI_Inv_persistentIdentity","v1.0","LacI_Inv","LacI_Inv", "LacI_Inv"),
				getFunctionalInstantiation_List(TetRIn, LacIOut, Inv), 
				getInteraction_List(interact1, interact2), null, null
				); 
	}
	
	private static Collection createCollection(SBOLDocument SBOL2Doc_test, List<String> collectionData)
	{	
		URI identity 		   = getURI(collectionData.get(0)); 
		URI persistentIdentity = getURI(collectionData.get(1)); 
		String version 		   = collectionData.get(2); 
		String displayId 	   = collectionData.get(3); 
		String name 		   = collectionData.get(4); 
		String description 	   = collectionData.get(5); 
		
		Collection collection = SBOL2Doc_test.createCollection(identity);
		setCommonTopLevelData(collection, identity, persistentIdentity, version, displayId, name, description);

		return collection; 
	}
	
	private static Component createComponentData(SBOLDocument SBOL2Doc_test, 
			Set<URI> type, Set<URI> roles,
			List<String> componentData,
			Structure structureData, 
			List<StructuralInstantiation> structureInstantiationData, 
			List<StructuralAnnotation> structureAnnotationData, 
			List<StructuralConstraint> structureConstraintData)
	{
		URI identity 		   = getURI(componentData.get(0));
		URI persistentIdentity = getURI(componentData.get(1));
		String version 		   = componentData.get(2);
		String displayId 	   = componentData.get(3);
		String name 		   = componentData.get(4);
		String description 	   = componentData.get(5);
		
		Component c = SBOL2Doc_test.createComponent(identity, type, roles);
		setCommonTopLevelData(c, identity, persistentIdentity, version, displayId, name, description);
		
		if(structureData != null)
			c.setStructure(structureData.getIdentity()); 
		if(structureInstantiationData != null)
		{
			c.setStructuralInstantiations(structureInstantiationData);
			if(structureAnnotationData != null && structureConstraintData == null)
				c.setStructuralAnnotations(structureAnnotationData);
			else if(structureConstraintData != null)
				c.setStructuralConstraints(structureConstraintData);
		}
		
		return c; 
	}
	
	private static FunctionalInstantiation createFunctionalInstantiationData(
			List<String> functionalInstantiation_data, 
			Component c)
	{	
		URI identity 		   = getURI(functionalInstantiation_data.get(0)); 
		URI persistentIdentity = getURI(functionalInstantiation_data.get(1)); 
		String version 		   = functionalInstantiation_data.get(2); 
		String displayId 	   = functionalInstantiation_data.get(3);
		String name 		   = functionalInstantiation_data.get(4); 
		String description 	   = functionalInstantiation_data.get(5); 
		
		AccessType access = null; 
		if(functionalInstantiation_data.get(6).equals("public"))
			access = AccessType.PUBLIC; 
		else if(functionalInstantiation_data.get(6).equals("private"))
			access = AccessType.PRIVATE; 
		
		DirectionType direction = null; 
		if(functionalInstantiation_data.get(7).equals("input"))
			direction = DirectionType.input;
		else if(functionalInstantiation_data.get(7).equals("output"))
			direction = DirectionType.output;
		else if(functionalInstantiation_data.get(7).equals("inout"))
			direction = DirectionType.inout; 
		else if(functionalInstantiation_data.get(7).equals("none"))
			direction = DirectionType.none; 
		
		URI instantiatedComponent = c.getIdentity();
		
		FunctionalInstantiation f = new FunctionalInstantiation(identity, access, instantiatedComponent, direction);
		setCommonDocumentedData(f, identity, persistentIdentity, version, displayId, name, description);

		
		return f;
	}
	
	private static Interaction createInteractionData(
			List<String> interaction_data, 
			List<Participation> participations,
			Set<URI> type)
	{	
		URI identity 		   = getURI(interaction_data.get(0)); 
		URI persistentIdentity = getURI(interaction_data.get(1));
		String version 		   = interaction_data.get(2);
		String displayId 	   = interaction_data.get(3); 
		String name 		   = interaction_data.get(4); 
		String description 	   = interaction_data.get(5); 
		
		Interaction interaction = new Interaction(identity, type, participations);
		
		setCommonDocumentedData(interaction, identity, persistentIdentity, version, displayId, name, description);

		
		return interaction;
	}
	
	private static MapsTo createMapTo (URI identity, RefinementType refinement, 
			FunctionalInstantiation pre_fi, FunctionalInstantiation post_fi)
	{
		return new MapsTo(identity, refinement, pre_fi.getIdentity(), post_fi.getIdentity());
	}
	

	private static Model createModelData(SBOLDocument doc, List<String> modeldata, Set<URI> roles)
	{	
		URI identity 		   = getURI(modeldata.get(0)); 
		URI persistentIdentity = getURI(modeldata.get(1)); 
		String version 		   = modeldata.get(2);
		String displayId 	   = modeldata.get(3); 
		String name 		   = modeldata.get(4);
		String description     = modeldata.get(5);  
		URI source 			   = getURI(modeldata.get(6)); 
		URI language 		   = getURI(modeldata.get(7));
		URI framework 		   = getURI(modeldata.get(8)); 
		
		Model model = doc.createModel(identity, source, language, framework, roles);
		setCommonTopLevelData(model, identity, persistentIdentity, version, displayId, name, description);
		
		return model;
	}
	
	private static Module createModuleData(SBOLDocument SBOL2Doc_test, 
			Set<URI> type, Set<URI> roles,
			List<String> module_data,
			List<FunctionalInstantiation> functionalInstantiation_data,
			List<Interaction> interactionData,
			List<ModuleInstantiation> moduleInstantiation_data,
			Set<URI> model_data)
	{
		URI identity 		   = getURI(module_data.get(0)); 
		URI persistentIdentity = getURI(module_data.get(1));
		String version 		   = module_data.get(2); 
		String displayId 	   = module_data.get(3); 
		String name 		   = module_data.get(4); 
		String description 	   = module_data.get(5);  
		
		Module m = SBOL2Doc_test.createModule(identity, roles);
		setCommonTopLevelData(m, identity, persistentIdentity, version, displayId, name, description);
		
		if(functionalInstantiation_data != null)
			m.setFunctionalInstantiations(functionalInstantiation_data);
		if(interactionData != null)
			m.setInteractions(interactionData);
		if(moduleInstantiation_data != null)
			m.setModuleInstantiations(moduleInstantiation_data);
		if(model_data != null)
			m.setModels(model_data);
		
		return m; 
	}
	
	private static ModuleInstantiation createModuleInstantiationData(
			List<String> moduleInstantiation_data, 
			Module m, 
			List<MapsTo> maps)
	{	
		URI identity 		   = getURI(moduleInstantiation_data.get(0)); 
		URI persistentIdentity = getURI(moduleInstantiation_data.get(1));
		String version 		   = moduleInstantiation_data.get(2); 
		String displayId 	   = moduleInstantiation_data.get(3); 
		String name 		   = moduleInstantiation_data.get(4); 
		String description 	   = moduleInstantiation_data.get(5); 
		
		ModuleInstantiation modInstantiation = new ModuleInstantiation(identity, m.getIdentity()); 
		setCommonDocumentedData(modInstantiation, identity, persistentIdentity, version, displayId, name, description);

		
		for(MapsTo map : maps)
			modInstantiation.addReference(map);
		return modInstantiation; 
	}
	
	private static Participation createParticipationData(
			URI identity, Set<URI> roles, FunctionalInstantiation fi)
	{
		return new Participation(identity, roles, fi.getIdentity());
	}
	
	private static StructuralAnnotation createStructuralAnnotationData(
			List<String> structuralAnnotations_data,
			StructuralInstantiation ref_structInstant,
			int startRange, int endRange)
	{
		URI identity 		   = getURI(structuralAnnotations_data.get(0));
		URI persistentIdentity = getURI(structuralAnnotations_data.get(1));
		String version 		   = structuralAnnotations_data.get(2);
		String displayId 	   = structuralAnnotations_data.get(3);
		String name 		   = structuralAnnotations_data.get(4);
		String description 	   = structuralAnnotations_data.get(5);
		Location location 	   = new Range(ref_structInstant.getIdentity(), startRange, endRange);
		
		StructuralAnnotation s = new StructuralAnnotation(identity, location);
		setCommonDocumentedData(s, identity, persistentIdentity, version, displayId, name, description);

		
		return s; 
	}
	
	private static StructuralConstraint createStructuralConstraintData(
			List<String> structuralConstraints_data,  
			StructuralInstantiation pre_structInstant, 
			StructuralInstantiation post_structInstant)
	{
		URI identity 		   = getURI(structuralConstraints_data.get(0));
		URI persistentIdentity = getURI(structuralConstraints_data.get(1));
		String version 		   = structuralConstraints_data.get(2);
		URI restriction 	   = getURI(structuralConstraints_data.get(3)); 
		URI subject 		   = pre_structInstant.getIdentity(); 
		URI object 			   = post_structInstant.getIdentity(); 
		
		StructuralConstraint s = new StructuralConstraint(identity, restriction, subject, object);
		
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		
		return s; 
	}
	
	private static StructuralInstantiation createStructuralInstantiationData(
			List<String> structuralInstantiations_data, 
			Component c)
	{
		URI identity 		   = getURI(structuralInstantiations_data.get(0));
		URI persistentIdentity = getURI(structuralInstantiations_data.get(1));
		String version 		   = structuralInstantiations_data.get(2);
		String displayId 	   = structuralInstantiations_data.get(3);
		String name 		   = structuralInstantiations_data.get(4);
		String description	   = structuralInstantiations_data.get(5);
		
		AccessType access = null; 
		if(structuralInstantiations_data.get(6).equals("public"))
			access = AccessType.PUBLIC; 
		else if(structuralInstantiations_data.get(6).equals("private"))
			access = AccessType.PRIVATE; 
		
		URI instantiatedComponent = c.getIdentity(); 
		
		StructuralInstantiation s = new StructuralInstantiation(identity, access, instantiatedComponent);
		setCommonDocumentedData(s, identity, persistentIdentity, version, displayId, name, description);

		
		return s; 
	}
	
	private static Structure createStructureData(SBOLDocument SBOL2Doc_test, List<String> structureData)
	{
		URI identity 		   = getURI(structureData.get(0)); 
		URI persistentIdentity = getURI(structureData.get(1)); 
		String version 		   = structureData.get(2); 
		String displayId 	   = structureData.get(3); 
		String name 		   = structureData.get(4); 
		String description 	   = structureData.get(5); 
		String element 		   = structureData.get(6); 
		URI encoding  		   = getURI(structureData.get(7));
		
		Structure structure = SBOL2Doc_test.createStructure(identity, element, encoding);
		setCommonTopLevelData(structure, identity, persistentIdentity, version, displayId, name, description);

		return structure;
	}
	
	/**
	 * data[] = identity
	 * data[] = persistentIdentity
	 * data[] = version
	 * data[] = displayID
	 * data[] = Name
	 * data[] = Description
	 * @param data
	 * @return
	 */
	private static List<String> getData(String ... data)
	{
		List<String> list = new ArrayList<String>();
		for(String d : data)
		{
			list.add(d);
		}
		return list;
	}
	
	private static List<FunctionalInstantiation> getFunctionalInstantiation_List(FunctionalInstantiation ... fi)
	{
		return new ArrayList<FunctionalInstantiation>(Arrays.asList(fi)); 
	}
	
	private static List<Interaction> getInteraction_List(Interaction ... i)
	{
		return new ArrayList<Interaction>(Arrays.asList(i)); 
	}
	
	private static List<MapsTo> getMapsTo_List(MapsTo ... maps)
	{
		return new ArrayList<MapsTo>(Arrays.asList(maps));
	}
	
	private static List<URI> getListOfURI(String ... appends)
	{
		List<URI> list = new ArrayList<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list; 
	}
	
	private static RefinementType getRefinement(String s)
	{
		if(s.equals("verifyIdentical"))
			return RefinementType.verifyIdentical; 
		else if(s.equals("useLocal"))
			return RefinementType.useLocal; 
		else if(s.equals("useRemote"))
			return RefinementType.useRemote; 
		else if(s.equals("merge"))
			return RefinementType.merge; 
		return RefinementType.merge; 
	}
	
	private static List<Participation> getParticipation_List(Participation ... p)
	{
		return new ArrayList<Participation>(Arrays.asList(p)); 
	}
	
	private static Set<URI> getSetOfURI(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list; 
	}
	
	private static List<StructuralAnnotation> getStructuralAnnotation_List(StructuralAnnotation ... sa)
	{
		return new ArrayList<StructuralAnnotation>(Arrays.asList(sa)); 
	}
	
	private static List<StructuralConstraint> getStructuralConstraint_List(StructuralConstraint ... sc)
	{
		return new ArrayList<StructuralConstraint>(Arrays.asList(sc)); 
	}
	
	private static List<StructuralInstantiation> getStructuralInstantiation_List(StructuralInstantiation ... si)
	{
		return new ArrayList<StructuralInstantiation>(Arrays.asList(si)); 
	}
	
	
	private static URI getURI(String append)
	{
		return URI.create("http://www.async.ece.utah.edu/" + append);
	}


}