using SystemManager.Core;
using SystemManager.Core.Models;
using SystemManager.Resources;
using SystemManager.Utils;

namespace SystemManager
{
    internal static class Program
    {
        [STAThread]
        static void Main(string[] args)
        {
            // TODO: Read level from args.
            var logLevel = Log.LEVEL_DEBUG;
            Log.Level = logLevel;

            if (args.Length == 0)
            {
                Log.E("Exiting. Missing arguments");
                Environment.Exit(2);
            }

            Log.I($"Starting. args({args.Length}): {string.Join(", ", args)}");

            if (args.Length == 1) LaunchExe(args[0]);
            else StartService(args);

            Application.Run();
        }

        private static void StartService(string[] args)
        {
            Log.D("Launching process watcher.");

            var exeList = Exe.ListFromArgs(args);
            var manager = new Manager();
            manager.Watch(exeList);
        }

        private static void LaunchExe(string exePath)
        {
            Log.D("Launching process.");

            if (File.Exists(exePath))
            {
                Task.Run(() =>
                    {
                        Launcher.Launch(exePath);
                        Application.Exit();
                    }
                );
            }
            else
            {
                MessageBox.Show(
                    Res.Strings.DialogErrorText + $"\"{exePath}\"",
                    Res.Strings.DialogErrorCaption,
                    MessageBoxButtons.OK
                );
            }
        }
    }
}
