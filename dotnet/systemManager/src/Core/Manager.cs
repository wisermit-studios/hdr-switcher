﻿using SystemManager.Core.Models;
using SystemManager.Utils;

namespace SystemManager.Core
{
    public class Manager
    {
        private bool initialHdrStatus = false;

        private readonly ProcessWatcher _processWatcher;

        public Manager()
        {
            _processWatcher = new ProcessWatcher();
        }

        public void Watch(List<Exe> exeList)
        {
            _processWatcher.Watch(
                exeList,
                onStart: () =>
                {
                    Log.I($"Process started.");
                    EnsureHdr();
                },
                onFinish: () =>
                {
                    Log.I($"Process ended.");
                    AttemptRevertHdr();
                }
            );
        }

        public void Stop()
        {
            _processWatcher.Dispose();
        }

        private void EnsureHdr()
        {
            initialHdrStatus = HdrManager.IsEnabled();

            if (!initialHdrStatus)
            {
                HdrManager.Toggle();
            }
        }

        private void AttemptRevertHdr()
        {
            if (HdrManager.IsEnabled() != initialHdrStatus)
            {
                HdrManager.Toggle();
            }
        }
    }
}