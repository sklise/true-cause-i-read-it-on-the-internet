class Fact < ActiveRecord::Migration
  def change
    create_table :facts do |t|
      t.string      :url
      t.string      :content
      t.timestamps
    end
  end
end
