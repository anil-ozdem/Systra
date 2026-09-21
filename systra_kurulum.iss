#define MyAppName "Systra"
#define MyAppVersion "2.1"
#define MyAppPublisher "Anıl Özdem"
#define MyAppJarName "Systra.jar"
#define MyIconName "systra.ico"

[Setup]
AppId={{8A8C349B-2345-429C-891A-123456789ABC}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={autopf}\{#MyAppName}
DisableProgramGroupPage=yes
PrivilegesRequired=admin
OutputDir=Output
OutputBaseFilename=SystraSetup
SetupIconFile={#MyIconName}
Compression=lzma
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
; Senin uygulaman
Source: "out\artifacts\Systra_jar\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; Simge dosyası
Source: "{#MyIconName}"; DestDir: "{app}"; Flags: ignoreversion
; Sisteme gömdüğümüz özel Java Motoru (JRE)
Source: "jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
; Artık sistemin değil, bizim gömdüğümüz jre'nin içindeki javaw.exe çalışacak
Name: "{autoprograms}\{#MyAppName}"; Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{app}\{#MyAppJarName}"""; IconFilename: "{app}\{#MyIconName}"; WorkingDir: "{app}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{app}\{#MyAppJarName}"""; IconFilename: "{app}\{#MyIconName}"; WorkingDir: "{app}"; Tasks: desktopicon

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Run]
Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{app}\{#MyAppJarName}"""; WorkingDir: "{app}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent runascurrentuser