package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        PetType rabbit = new PetType();
        rabbit.setName("Rabbit");
        PetType savedRabbitType = petTypeService.save(rabbit);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kshitiz");
        owner1.setLastName("Bista");
        owner1.setAddress(" Bhainsepati");
        owner1.setCity("Kathmandu");
        owner1.setTelephone("+911 67098");

        Owner.builder().address("abc").build();

        Pet kshitizPet = new Pet();
        kshitizPet.setPetType(savedDogType);
        kshitizPet.setOwner(owner1);
        kshitizPet.setBirthDate(LocalDate.now());
        kshitizPet.setName("Rosco");

        owner1.getPets().add(kshitizPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Ram");
        owner2.setLastName("Bista");
        owner2.setAddress(" Bhainsepati");
        owner2.setCity("Kathmandu");
        owner2.setTelephone("+911 67098");

        Pet ramCat = new Pet();
        ramCat.setName("Just Cat");
        ramCat.setBirthDate(LocalDate.now());
        ramCat.setPetType(savedCatType);
        ramCat.setOwner(owner2);

        owner2.getPets().add(ramCat);
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Sushma");
        owner3.setLastName("Giri");
        owner3.setAddress(" ktm");
        owner3.setCity("Kathmandu");
        owner3.setTelephone("+911 67098111");

        Pet sushmaRabbit = new Pet();
        sushmaRabbit.setName("puntu");
        sushmaRabbit.setBirthDate(LocalDate.now());
        sushmaRabbit.setPetType(savedRabbitType);
        sushmaRabbit.setOwner(owner3);

        owner3.getPets().add(sushmaRabbit);
        ownerService.save(owner3);

        System.out.println("Loaded Owners...");

        Visit catVisit = new Visit();
        catVisit.setPet(ramCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Turner");

        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
