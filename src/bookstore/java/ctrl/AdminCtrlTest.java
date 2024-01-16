package bookstore.java.ctrl;

import bookstore.java.cmp.*;
import bookstore.java.ctrl.AdminCtrl;
import bookstore.java.model.AdminModel;
import bookstore.java.view.AdminView;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AdminCtrlTest {

    private AdminModel model;
    private AdminView view;
    private AdminCtrl controller;

    @Before
    public void setUp() {
        model = mock(AdminModel.class);
        view = mock(AdminView.class);
        controller = new AdminCtrl(model, view);
    }

    @Test
    public void testEmpListBtnAction() {
        // TODO: Implement testEmpListBtnAction
    }

    @Test
    public void testAddBookBtnAction() {
        // TODO: Implement testAddBookBtnAction
    }

    @Test
    public void testIncrStkBtnAction() {
        // TODO: Implement testIncrStkBtnAction
    }

    @Test
    public void testCheckoutBtnAction() {
        // TODO: Implement testCheckoutBtnAction
    }

    @Test
    public void testFullInfoBtnAction() {
        // TODO: Implement testFullInfoBtnAction
    }

    @Test
    public void testAddEmpBtnAction() {
        // TODO: Implement testAddEmpBtnAction
    }

    @Test
    public void testRemEmpBtnAction() {
        // TODO: Implement testRemEmpBtnAction
    }

    @Test
    public void testEditEmpBtnAction() {
        // TODO: Implement testEditEmpBtnAction
    }
}