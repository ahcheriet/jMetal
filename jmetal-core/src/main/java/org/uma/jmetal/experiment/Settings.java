//  Settings.java
//
//  Authors:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.experiment;

import org.uma.jmetal.core.Algorithm;
import org.uma.jmetal.core.Operator;
import org.uma.jmetal.core.Problem;
import org.uma.jmetal.encoding.solutiontype.ArrayRealSolutionType;
import org.uma.jmetal.encoding.solutiontype.BinaryRealSolutionType;
import org.uma.jmetal.encoding.solutiontype.BinarySolutionType;
import org.uma.jmetal.encoding.solutiontype.RealSolutionType;
import org.uma.jmetal.operator.crossover.Crossover;
import org.uma.jmetal.operator.crossover.CrossoverFactory;
import org.uma.jmetal.operator.mutation.Mutation;
import org.uma.jmetal.operator.mutation.MutationFactory;
import org.uma.jmetal.util.JMetalException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Properties;

/**
 * Class representing Settings objects.
 */
public abstract class Settings {
  protected Problem problem;
  protected String problemName;
  protected String paretoFrontFile;

  /** Constructor */
  public Settings() {
  }

  /** Constructor */
  public Settings(String problemName) throws JMetalException {
    this.problemName = problemName;
  }

  /** configure() method */
  abstract public Algorithm configure() throws JMetalException;

  /** configure() method based on reading a properties file */
  abstract public Algorithm configure(Properties configuration) throws JMetalException;

  /**
   * Configure() method. Change the default configuration
   *
   * @param settings
   * @return A problem with the experiment.settings indicated as argument
   * @throws org.uma.jmetal.util.JMetalException
   * @throws ClassNotFoundException
   */
  // FIXME: to be revised
  /*
  public final Algorithm configure(HashMap settings)
    throws JMetalException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
    if (settings != null) {
      Field[] fields = this.getClass().getFields();
      for (int i = 0; i < fields.length; i++) {
        if (fields[i].getName().endsWith("_")) {
          // it is a configuration field
          if (fields[i].getType().equals(int.class) ||
            fields[i].getType().equals(Integer.class)) {
            if (settings.containsKey(fields[i].getName())) {
              Integer value = (Integer) settings.get(fields[i].getName());
              fields[i].setInt(this, value.intValue());
            }
          } else if (fields[i].getType().equals(double.class) ||
            fields[i].getType().equals(Double.class)) {
            Double value = (Double) settings.get(fields[i].getName());

            if (settings.containsKey(fields[i].getName())) {
              if ("mutationProbability".equals(fields[i].getName()) &&
                value == null) {
                if ((RealSolutionType.class == problem.getSolutionType().getClass()) ||
                  (ArrayRealSolutionType.class == problem.getSolutionType().getClass())) {
                  value = 1.0 / problem.getNumberOfVariables();
                } else if (BinarySolutionType.class == problem.getSolutionType().getClass() ||
                  BinaryRealSolutionType.class == problem.getSolutionType().getClass()) {
                  int length = problem.getNumberOfBits();
                  value = 1.0 / length;
                } else {
                  int length = 0;
                  for (int j = 0; j < problem.getNumberOfVariables(); j++) {
                    length += problem.getLength(j);
                  }
                  value = 1.0 / length;
                }
                fields[i].setDouble(this, value);
              } else {
                fields[i].setDouble(this, value);
              }
            }
          } else {
            Object value = settings.get(fields[i].getName());
            if (value != null) {
              if (fields[i].getType().equals(Crossover.class)) {
                Object value2 = CrossoverFactory.getCrossoverOperator((String) value, settings);
                value = value2;
              }
              if (fields[i].getType().equals(Mutation.class)) {
                Object value2 = MutationFactory.getMutationOperator((String) value, settings);
                value = value2;
              }
              fields[i].set(this, value);
            }
          }
        }
      }

      // At this point all the fields have been read from the properties
      // parameter. Those fields representing crossover and mutations should also
      // be initialized. However, there is still mandatory to configure them
      for (int i = 0; i < fields.length; i++) {
        if (fields[i].getType().equals(Crossover.class) ||
          fields[i].getType().equals(Mutation.class)) {
          Operator operator = (Operator) fields[i].get(this);
          // This field stores a crossover operator
          String tmp = fields[i].getName();
          String aux = fields[i].getName().substring(0, tmp.length() - 1);

          for (int j = 0; j < fields.length; j++) {
            if (i != j) {
              if (fields[j].getName().startsWith(aux)) {
                // The field is a configuration parameter of the crossover
                tmp = fields[j].getName().substring(aux.length(), fields[j].getName().length() - 1);

                if (fields[j].get(this) != null) {
                  if (fields[j].getType().equals(int.class) ||
                    fields[j].getType().equals(Integer.class)) {
                    operator.setParameter(tmp, fields[j].getInt(this));
                  } else if (fields[j].getType().equals(double.class) ||
                    fields[j].getType().equals(Double.class)) {
                    operator.setParameter(tmp, fields[j].getDouble(this));
                  }
                }
              }
            }
          }
        }
      }

      paretoFrontFile = (String) settings.get("paretoFrontFileList");
    }

    return configure();
  }
*/
}
