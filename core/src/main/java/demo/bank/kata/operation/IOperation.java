package demo.bank.kata.operation;

import demo.bank.kata.action.IAction;

import java.util.List;

public interface IOperation {
    List<IAction> getActionList();
}
