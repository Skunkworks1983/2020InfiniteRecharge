package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class ConditionalCommand extends edu.wpi.first.wpilibj2.command.ConditionalCommand
{
    //private Function<Void, Boolean> condition;

    private BooleanSupplier condition;

    public ConditionalCommand(Command onTrue, Command onFalse, BooleanSupplier condition)
    {
        super(onTrue, onFalse, condition);
        this.condition = condition;
    }

    public ConditionalCommand(Command onTrue, BooleanSupplier condition)
    {
        this(onTrue, null, condition);
    }

    protected boolean condition()
    {
        return condition.getAsBoolean();
    }
}