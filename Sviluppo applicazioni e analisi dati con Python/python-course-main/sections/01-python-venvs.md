# Venv: Python Virtual Environments

Virtual environments are *sand-boxes* where you implement your projects with packages (or modules) updated to different versions instead of other projects or global packages (or modules), using pip. 

Moreover, venv allows to install modules with pip without root privileges, with all the security advantages that this brings.

Concretely, the result is a folder that contains all the necessary files for the environment working and a copy of the Python binary; inside of the Python binary, we can install all the modules updated at the version that we need based on our projects specs; this will require the activation of the environment in order to be utilized. 

## Create a Virtual Environment

All the following operations are executed within the ***terminal*** (or ***command-prompt*** in *windows*).

> [!NOTE]
>
> 🔥 Pip is the default Python package manager. </br>
> ☝🏼 All the necessary tools that are needed to manage the venv run on the terminal.

```bash
pip list
```

> Output: shows all packages installed in the global environment.

To create a venv navigate to the target folder, then type:

```bash
python -m venv environment_name
```

Instead, to create the venv directly in the target folder without previously navigating into it, type:

```bash
python -m venv folder/path/environment_name
```

> Output: creates the venv and populates it with all the necessary files.

The main subfolders and files are (on Linux, update with the Windows version):
<!-- TODO: convert in table -->
- On Windows: 
    ```
    Scripts (important!)
    Include
    Lib
    pyenv.cfg
    ...
    ```
- On Linux
    ```
    bin (important!)
    include
    lib
    lib64
    pyenv.cfg
    ...
    ```
    
After the creation of a venv, the activation in needed in order to work in it; to do so, navigate into the venv folder as follows: 

```bash
#On Linux
cd folder/path/environment_name
```

... and then type the following to activate the environment: 

```bash
#On Linux
bin/activate
```

```bash
#On Windows
\Scripts\activate.bat
```

> Output: activates the venv (see the venv name at the start of the  line).

If now pip list is launched, the venv packages are showed. If which python si typed, the venv path is showed.

```bash
pip list    
```

> Output: shows all packages installed in the venv.

> [!NOTE]
>
> Typically, packages installed inside of the venv should be different from packages installed in the global env. Nevertheless, it does not apply if is the first global installation.

```bash
which python # On Linux
where python # On Windows
```

> Output: shows the venv folder path.

Now is possible to install all the necessary packages inside your environment, once it is activated! 

## Manage a Virtual Environment

The virtual env is not meant as a working environment where to develop; instead, it is an environment that can help to develop. 

How it is possible to share a project, letting other people know the modules used and the associated versions? Simply, saving inside of a `.txt` file conventionally called `requirements.txt`. 

To do so, use the command **pip freeze** as follows and generate a list of all the used packages:

```bash
pip freeze > requirements.txt
```

> Output: a `.txt` file where all the packages and versions used are reported.

It is possible to **deactivate** the environment via the following command: 

```bash
deactivate          
```

> Output: deactivates the virtual environment in use.

It is also possible to delete the virtual environment using the following command:

```bash
# On Linux
rm -r environment_name
```

> Output: deletes the virtual environment with the selected name.

> [!WARNING]
>
> ⚠️ In **VS Code**, on **Windows**, simply be sure that the environment is deactivated, then delete the venv folder to remove the related virtual environment.

If you are a second developer which needs to install an existing project in a venv with all the relatives modules needed, the following passages are necessary to do so: 

```bash
#Installing a new venv
python -m venv v_env_name
#Activating the new venv
source  v_env_name/bin/activate
#Show the packages installed - only the initial ones are present
pip list
#Installing the necessary requirements
pip install -r requirements.txt
#Show the packages installed - now all the necessary are present
pip list
```

> Output: Creates the new environment and imports all the necessary packages updated at the right version.
 

## List Unused Packages in Your Project: PyCharm and the `code-inspection-tool`

Sometimes is useful to understand which packages and relative dependencies has not been used in your project. 

To do so, you can leverage the *`code-inspection-tool`* provided by PyCharm.
<!-- TODO: add an internal link from Pycharm to the IDE section -->

For further reference, look at [Delete unused packages from requirements file](https://stackoverflow.com/questions/25376213/delete-unused-packages-from-requirements-file).

To perform the code inspection, open Pychar. Then:

1. Import the project in PyCharm;
2. Create a `requirements.txt` file using the `pip freeze > requirements.txt` command via pip;
3. Delete all the `requirements.txt` content;
4. Run the PyCharm code-inspector-tool via `code > Inspect code` command;
5. Let the inspector run;
6. In the result pop-up open the Python section;
7. The section will contain one of the following messages: 
    - `Package requirement '<package>' is not satisfied` if there is any package that is listed in `requirements.txt` but not used in any .py file;
    - `Package '<package>' is not listed in project requirements` if there is any package that is used in .py files, but not listed in `requirements.txt`;
8. The second inspection is the one that we are looking for;
9. Correct the `requirements.txt` file by following the PyCharm suggestions and commands;
10. Clean the venv (or even create a new one) and import the selected packages.
