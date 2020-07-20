package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNER_5 = "redirect:/owners/5";

    @Mock(lenient = true)
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        BDDMockito.given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();

                    String name = invocation.getArgument(0);

                    if (name.equals("%Buck%")) {
                        owners.add(new Owner(1L, "Joe", "Buck"));
                        return owners;
                    } else if (name.equals("%DontFindMe%")) {
                        return owners;
                    } else if (name.equals("%FindMe%")) {
                        owners.add(new Owner(1L, "Joe", "Buck"));
                        owners.add(new Owner(2L, "Joe2", "Buck2"));
                        return owners;
                    }

                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void processFindFormWildcardFound() {
        //given
        Owner owner = new Owner(1L, "Joe", "FindMe");
        InOrder inOrder = inOrder(ownerService, model);

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        //then
        Assertions.assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        Assertions.assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        // in order verification
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void processFindFormWildcardStringAnnotation() {
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        Assertions.assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        Assertions.assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
        verifyNoInteractions(model);
    }


    @Test
    void processFindFormWildcardNotFound() {
        //given
        Owner owner = new Owner(1l, "Joe", "DontFindMe");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        Assertions.assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        Assertions.assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
        verifyNoInteractions(model);
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