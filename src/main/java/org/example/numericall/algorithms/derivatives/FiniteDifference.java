package org.example.numericall.algorithms.derivatives;

import org.example.numericall.functions.MathFunction;

public class FiniteDifference
{
    public static MathFunction derivativeOf(MathFunction function, int order, double hStepSize)
    {
        MathFunction derivative = null;
        if (order == 1)
        {
            derivative = x -> (function.apply(x + hStepSize) - function.apply(x - hStepSize)) / (2 * hStepSize);
        }
        else if (order > 1)
        {
            MathFunction previousDerivative = derivativeOf(function, order - 1, hStepSize);
            derivative = x -> (previousDerivative.apply(x + hStepSize) - previousDerivative.apply(x - hStepSize)) / (2 * hStepSize);

        }
        return derivative;
    }


}
