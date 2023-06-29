package jeu.hebergeur.controller;

import jeu.hebergeur.controller.HebergeurController;
import jeu.hebergeur.model.Hebergeur;
import jeu.hebergeur.service.HebergeurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(args = "--server.port=8091")
@AutoConfigureMockMvc
public class HebergeurControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HebergeurService hebergeurService;

    @Mock
    private Hebergeur hebergeur;

    private HebergeurController hebergeurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hebergeurController = new HebergeurController();
      //  mockMvc = MockMvcBuilders.standaloneSetup(hebergeurController).build();
    }

    @Test
    void afficherDetailsHebergeur() throws Exception {
        when(hebergeur.getJoueurs()).thenReturn(new ArrayList<>());
        when(hebergeur.getIsFull()).thenReturn(false);

        Hebergeur result = hebergeurController.afficherDetailsHebergeur();
        if (result != null) {
            assertEquals(new ArrayList<>(), result.getJoueurs());
            assertEquals(false, result.getIsFull());

            verify(hebergeur).getJoueurs();
            verify(hebergeur).getIsFull();
        }

    }

    @Test
    void isFull() throws Exception {
        if (hebergeur != null)  {
            when(hebergeur.getIsFull()).thenReturn(true);

            boolean result = hebergeurController.isFull();

            assertEquals(true, result);

            verify(hebergeur).getIsFull();
        }

    }



    @Test
    void lancerPartie() throws Exception {
       if (hebergeur != null) {

           hebergeurController.lancerPartie();

           verify(hebergeurService, times(13)).jouerTour();
       }
    }
}
