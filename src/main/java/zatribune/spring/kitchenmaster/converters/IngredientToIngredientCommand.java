package zatribune.spring.kitchenmaster.converters;

import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import zatribune.spring.kitchenmaster.commands.IngredientCommand;
import zatribune.spring.kitchenmaster.data.entities.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitMeasureToUnitMeasureCommand unitMeasureConverter;

    @Autowired
    public IngredientToIngredientCommand(UnitMeasureToUnitMeasureCommand unitMeasureConverter) {
        this.unitMeasureConverter = unitMeasureConverter;
    }

    @Synchronized
    @Override
    public @NonNull
    IngredientCommand convert(Ingredient source) {
        final IngredientCommand ingredient = new IngredientCommand();
        if (source.getId() != null)
            ingredient.setId(source.getId().toString());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        if (source.getUnitMeasure() != null)
            ingredient.setUnitMeasure(unitMeasureConverter.convert(source.getUnitMeasure()));
        return ingredient;
    }
}
