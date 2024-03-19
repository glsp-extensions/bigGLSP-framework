<!-- TITLE -->
<h1 align="center">&emsp;bigGLSP Framework</h1>

</br>
</br>

<div align="center">

**[DOCUMENTATION](#documentation) •
[EXAMPLES](#examples) •
[CONTRIBUTING](#contributing) •
[LICENSE](#license)**

</div>

</br>

## Documentation

Learn fundamentals or explore advanced topics.

- [Documentation](./docs/README.md)

## Examples

- [bigUML](https://github.com/borkdominik/bigUML): UML editor for VSCode!

## Building

### Requirements

The Server is developed with the following **Eclipse IDE** version:

- **Name**: Eclipse Modeling Tools
- **Version**: 2023-12
- **Download**: <https://www.eclipse.org/downloads/packages/release/2023-12/r/eclipse-modeling-tools>
- 2024 version will not work.

You also need the following installations:

- **Java 17 JDK**

### Install Eclipse

Download and install/extract the Eclipse IDE.

### Install Lombok

Within the `jars` folder, there is `lombok.jar`. Execute within the terminal

`java -jar lombok.jar`

Do the steps required in the wizard. More information can be taken from the [lombok documentation](https://projectlombok.org/setup/eclipse).

### Install Maven

You first need to install the following plugin:

- The M2Eclipse (Maven Integration for Eclipse) plugin:
  - Install it via `Help > Install New Software`
  - Maven Integration for Eclipse Update Site - <http://download.eclipse.org/technology/m2e/releases/latest>
  - Install _Maven Integration for Eclipse_

### Import Projects

Afterward, you can import the projects:

- Via `File > Open Projects from File System`.
- Import the bigGLSP framework, e.g., `<path>/bigGLSP-framework`

Then, you have to set the active target platform once to resolve all necessary plugins. To do so, open `targetplatform.target` (located in the module `./targetplatforms`) and hit `Set as Active Target Platform` in the Target Editor. After the target platform is set, you can reload the target platform on demand.

Sometimes the Eclipse IDE does not compile correctly - restarting and setting the targetplatform again helps.

## Eclipse Recommendations

- Create a key shortcut for `Build Clean` (`Window > Preferences > General > Keys > 'Build Clean'`), e.g., `Ctrl + Alt + C`, because **very often** the Eclipse IDE can not build the project and fails. Cleaning the project before starting it again helps - there is ongoing work to move away from the Eclipse IDE.

## Contributing

Contributions of any kind are welcome! Do not hesitate to report a bug or to request a feature. Feel free to [open Issues](./issues) or submit PRs.

If you like our work, please feel free to [buy us a coffee](https://www.buymeacoffee.com/bigERtool) ☕️

<a href="https://www.buymeacoffee.com/bigERtool" target="_blank">
  <img src="https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png" alt="Logo" >
</a>

## License

The project is distributed under the [MIT](https://github.com/borkdominik/bigUML/blob/main/LICENSE) License. See [License](https://github.com/borkdominik/bigUML/blob/main/LICENSE) for more details.
