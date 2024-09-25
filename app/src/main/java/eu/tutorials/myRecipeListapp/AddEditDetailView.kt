package eu.tutorials.myRecipeListapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorials.myRecipeListapp.data.Recipe
import eu.tutorials.mywishlistapp.R
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: RecipeViewModel,
    navController: NavController
){

    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L){
        val recipe = viewModel.getArecipeByID(id).collectAsState(initial = Recipe(0L, "", ""))
        viewModel.RecipeTitleState = recipe.value.title
        viewModel.RecipeDescriptionState = recipe.value.description
    }else{
        viewModel.RecipeTitleState = ""
        viewModel.RecipeDescriptionState = ""
    }

    Scaffold(
        topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.change_recipe)
    else stringResource(id = R.string.add_Recipe)
    ) {navController.navigateUp()}
    },
        scaffoldState = scaffoldState
        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            RecipeTextField(label = "Title",
                value = viewModel.RecipeTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            RecipeTextField(label = "Description",
                value = viewModel.RecipeDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick={
                if(viewModel.RecipeTitleState.isNotEmpty() &&
                    viewModel.RecipeDescriptionState.isNotEmpty()){
                    if(id != 0L){
                        viewModel.updateRecipe(
                            Recipe(
                                id = id,
                                title = viewModel.RecipeTitleState.trim(),
                                description = viewModel.RecipeDescriptionState.trim()
                            )
                        )
                    }else{
                        //  AddWish
                        viewModel.addRecipe(
                            Recipe(
                                title = viewModel.RecipeTitleState.trim(),
                                description = viewModel.RecipeDescriptionState.trim())
                        )
                        snackMessage.value = "Recipe has been added"
                    }
                }else{
                    //
                    snackMessage.value = "Enter fields to Add recipe"
                }
                scope.launch {
                    //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }){
                Text(
                    text = if (id != 0L) stringResource(id = R.string.change_recipe)
                    else stringResource(
                        id = R.string.add_Recipe
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}


@Composable
fun RecipeTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.blue),
            unfocusedBorderColor = colorResource(id = R.color.blue),
            cursorColor = colorResource(id = R.color.blue),
            focusedLabelColor = colorResource(id = R.color.blue),
            unfocusedLabelColor = colorResource(id = R.color.blue),
        )


    )
}

@Preview
@Composable
fun WishTestFieldPrev(){
    RecipeTextField(label = "text", value = "text", onValueChanged = {})
}

