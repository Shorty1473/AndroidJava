package teamroot.Math;

public class Floats
{
	public static void Normalize(float[]Arr)
	{
		float l_ = 0;
		
		for(int i = 0; i < Arr.length; ++i)
			l_ += (Arr[i] * Arr[i]);
		
		if(l_ == 0)
			return;
		
		l_ = (float)Math.sqrt(l_);
		
		for(int i = 0; i < Arr.length; ++i)
			Arr[i] = Arr[i] / l_;
	}
	
	public static Float3 Lerp(Float3 lhs, Float3 rhs, float value)
	{
		//Regular: lhs + (value * (rhs - lhs))
		//Precise: ((1 - value) * lhs) + (value * rhs)
		
		Float3 o_ = new Float3(lhs).Mul(1.0f - value);
		Float3 h_ = new Float3(rhs).Mul(value);
		
		return (o_.Add(h_));
	}
}
