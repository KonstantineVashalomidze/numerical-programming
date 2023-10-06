package org.example.numericall.algorithms.derivatives;

import org.example.numericall.functions.MathFunction;

public class FiniteDifference
{
    public static MathFunction firstDerivativeOf(MathFunction function, double hStepSize)
    {
        MathFunction mathFunction = null;
        for (; hStepSize > 0; hStepSize -= 0.01)
        {
            double finalHStepSize = hStepSize;
            mathFunction = x -> (function.apply(x + finalHStepSize) - function.apply(x - finalHStepSize)) / (2 * finalHStepSize);

        }
        return mathFunction;
    }


}
