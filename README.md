# insynium
plm-project
- Context
	- PLM software editor: Collaborative product design solution (satellites, aircraft, car, clothing, robots, ...)

- Business
	- Product conception requires to manage its data:
		- Part:
			- It Represents a physiscal element or assembly of physical elements. For example a car (product) is composed among other of 4 wheels (4 occurrences of the Part "Wheel").
			  The wheel it self is composed by 1 wheel rim (1 occurrence of the Part "Rim"), 1 tyre (1 occurrence of the Part "Tyre") and X nuts (X occurrences of the Part "Nuts")
		- Document:
			- It represents the documentation (Studies, business requirements, tests, maintenance, ...)
			
		- A Document can be linked to a Part, in such case both share the same life cycle template and all actions applied to the part is automatically applied to all linked documents 

- Services
	- Reservation: In a collaborative context, modifying an entity requires to reserve it as first to avoid concurrent updates
	- Life cycle:  All entity evolve over time, it goes through different steps nammed life cycle states. Each entity as a defined set of life cycle states which is named the life cycle template.
			       For exemple a design Part freshly created by a designer starts in the "In progress" state. Once the designer job is done, it must be validated by other people and so the Part goes in the "Under validation" state.
				   When the design of the Part has been validated, it goes into the final state: "Release"
	- Versionning: An entity in its final life cycle state can restart a new cycle by creating a new version if some modifications are required. The first version is not lost and still exist.

- Rules
	- An entity cannot be updated if it is not reserved. If reserved, an entity can be updated only by the user who has reserved it
	- An entity life cycle state cannot be modified if it is reserved
	- An entity in its final life cycle state cannot be reserved and its life cycle state cannot be modified
	- A new version can be created only if the current version is in its final life cycle state
	- Any service cannot be applied to a Document linked to a Part, services must be launched from the Part and is automatically applied to all linked Document
	- if a service cannot be executed because of one of the previous rules the invoker must be notified with an explicit reason

- Starting point
	- POC

- Technical stack
	- JAVA
	- Spring
	- Hibernate

- Mission
	- Industrialisation (POC must be refactored)
    - Estimation (arround 6h)

- Expected improvement
	1 Data integrity is essential (this includes entity and their related entities)
	2 Despite this, bugs related to data quality is very common (i.e. often bugs are not reproducible outside of the PROD)
	3 Performances must be optimals but this no real time software (i.e. code simplicity > micro optimisation)
	4 Some new entities will be added soon, different than Part and Document but with the same behaviors but necessarily all of them (i.e. réservation/life cycle/versionning)
	5 We have customers all arround the world, and so are their users too
	6 Integration (i.e. solution customisation to fit customer specific needs) maybe done will by others companies
	  Services offered by the solution must be easy to reuse for developpers

- Not expected improvement
	- User management (creation, loggin, ...)
	- Web Security or any security related topic
	- Spring configuration (data source, hibernate, ...)
	- Rest services input/ouput kind (current Controller methods signature are ok as-is)
	- Build, no maven nor gradle

- Deliverables
	- JAVA source code only
	- Any other kind of content can be just described
	- 

