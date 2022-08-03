package plm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plm.dao.DocumentDao;
import plm.model.Document;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentDao documentDao;
	
    public void reserve(String userId, String reference, String version, int iteration) {
		
    	Document document = documentDao.get(reference, version, iteration);
    	
    	if (isNotLinkedToPart(document) && !document.isReserved() && !document.getLifeCycleTemplate().isFinal(document.getLifeCycleState())) {

    		Document nextIteration = new Document(document.getReference(), document.getVersion(), iteration + 1);
    		
    		nextIteration.setReserved(true);
    		nextIteration.setReservedBy(userId);
    		
    		nextIteration.setLifeCycleTemplate(document.getLifeCycleTemplate());
    		nextIteration.setLifeCycleState(document.getLifeCycleState());

    		nextIteration.setVersionSchema(document.getVersionSchema());
    		
    		nextIteration.setDocumentAttribute1(document.getDocumentAttribute1());
    		nextIteration.setDocumentAttribute2(document.getDocumentAttribute2());
    		
    		documentDao.create(nextIteration);
    	}
	}

	public void update(String userId, String reference, String version, int iteration, String documentAttribute1, String documentAttribute2) {

    	Document document = documentDao.get(reference, version, iteration);

    	if (document.isReserved() && document.getReservedBy().equals(userId)) {
    		
    		document.setDocumentAttribute1(documentAttribute1);
    		document.setDocumentAttribute2(documentAttribute2);
    		
    		documentDao.update(document);
    	}
	}

	public void free(String userId, String reference, String version, int iteration) {
		
    	Document document = documentDao.get(reference, version, iteration);
    	
    	if (isNotLinkedToPart(document) && document.isReserved() && document.getReservedBy().equals(userId)) {

    		document.setReserved(false);
    		document.setReservedBy(null);

    		documentDao.update(document);
    	}
	}

	public void setState(String userId, String reference, String version, int iteration, String state) {
		
    	Document document = documentDao.get(reference, version, iteration);
    	
    	if (isNotLinkedToPart(document) && !document.isReserved() && document.getLifeCycleTemplate().isKnown(state)) {
    		
    		document.setLifeCycleState(state);

    		documentDao.update(document);
    	}
	}

	public void revise(String userId, String reference, String version, int iteration) {
		
    	Document document = documentDao.get(reference, version, iteration);
    	
    	if (isNotLinkedToPart(document) && !document.isReserved() && document.getLifeCycleTemplate().isFinal(document.getLifeCycleState())) {

    		Document nextVersion = new Document(document.getReference(), document.getVersionSchema().getNextVersionLabel(version), 1);
    		
    		nextVersion.setReserved(false);
    		nextVersion.setReservedBy(null);
    		
    		nextVersion.setLifeCycleTemplate(document.getLifeCycleTemplate());
    		nextVersion.setLifeCycleState(document.getLifeCycleTemplate().getInitialState());

    		nextVersion.setVersionSchema(document.getVersionSchema());
    		
    		nextVersion.setDocumentAttribute1(document.getDocumentAttribute1());
    		nextVersion.setDocumentAttribute2(document.getDocumentAttribute2());
    		
    		documentDao.create(nextVersion);
    	}
	}

	private boolean isNotLinkedToPart(Document document) {
    	//
		// Implementation and returned value are not relevant for this exercise
    	//
		return false;
	}
}
