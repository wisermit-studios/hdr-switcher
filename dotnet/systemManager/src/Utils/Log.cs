namespace SystemManager.Utils
{
    public static class Log
    {
        public const int LEVEL_NONE = 0;
        public const int LEVEL_ERROR = 3;
        public const int LEVEL_WARNING = 4;
        public const int LEVEL_INFO = 6;
        public const int LEVEL_DEBUG = 7;

        public static int Level { get; set; } = LEVEL_NONE;

        public static void D(string message)
        {
            if (Level >= LEVEL_DEBUG)
            {
                Console.WriteLine($"D, {message}");
            }
        }

        public static void I(string message)
        {
            if (Level >= LEVEL_INFO)
            {
                Console.WriteLine($"I, {message}");
            }
        }

        public static void E(string message)
        {
            if (Level >= LEVEL_ERROR)
            {
                Console.WriteLine($"E, {message}");
            }
        }
    }
}