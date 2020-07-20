package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNER_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void processFindFormWildcardString(){
        // Given
        Owner owner = new Owner(1L, "John", "Smith");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);
        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);
        // Then
        Assertions.assertThat("%Smith%").isEqualTo(captor.getValue());
    }

    @Test
    void processFindFormWildcardStringAnnotation(){
        // Given
        Owner owner = new Owner(1L, "John", "Smith");
        List<Owner> ownerList = new ArrayList<>();
        BDDMockito.given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);
        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);
        // Then
        Assertions.assertThat("%Smith%").isEqualTo(stringArgumentCaptor.getValue());
    }

    @Test
    void processCreationFormHasErrors() {
        // Given
        Owner owner = new Owner(1L, "John", "Smith");
        BDDMockito.given(bindingResult.hasErrors()).willReturn(true);
        // When
        String viewName = ownerController.processCreationForm(owner, bindingResult);
        // Then
        Assertions.assertThat(viewName).isEqualToIgnoringCase(CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    void processCreationFormNoErrors() {
        // Given
        Owner owner = new Owner(5L, "John", "Smith");
        BDDMockito.given(bindingResult.hasErrors()).willReturn(false);
        BDDMockito.given(ownerService.save(ArgumentMatchers.any())).willReturn(owner);
        // When
        String viewName = ownerController.processCreationForm(owner, bindingResult);
        // Then
        Assertions.assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNER_5);
    }
}