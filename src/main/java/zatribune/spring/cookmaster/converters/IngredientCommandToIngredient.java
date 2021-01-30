package zatribune.spring.cookmaster.converters;

import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import zatribune.spring.cookmaster.commands.IngredientCommand;
import zatribune.spring.cookmaster.data.entities.Ingredient;
import zatribune.spring.cookmaster.data.entities.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitMeasureCommandToUnitMeasure unitMeasureConverter;

    @Autowired
    public IngredientCommandToIngredient(UnitMeasureCommandToUnitMeasure unitMeasureConverter) {
        this.unitMeasureConverter = unitMeasureConverter;
    }

    @Synchronized
    @Override
    public Ingredient convert(@Nullable IngredientCommand source) {
        if (source == null)
            return null;
        final Ingredient ingredient = new Ingredient();
        if (source.getId() == null||source.getId().isEmpty())
            ingredient.setId(new ObjectId());
        else
            ingredient.setId(new ObjectId(source.getId()));
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitMeasure(unitMeasureConverter.convert(source.getUnitMeasure()));
        return ingredient;
    }
}
